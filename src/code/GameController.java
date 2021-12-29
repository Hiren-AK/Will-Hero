package code;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable, Serializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    private HighScore score = new HighScore();
    private GameController currentGame;
    private TranslateTransition backgroundAnimation;

    private GameController game;
    private int currentScore;
    private ArrayList<GameObject> gameObjects;
    private HighScore highScore;                        //an object of type HighScore to help in serialization
    private FileOutputStream fileOut;                   //the file writer to write to the file that we will serialise to
    private ObjectOutputStream out;                     //the object writer that will write to the file using the file writer
    private FileInputStream fileIn;                     //the file reader to read from the file that we will serialise to
    private ObjectInputStream in;                       //the object reader that will read from the file using the file reader
    private int lastSavedGameIndex;
    private ArrayList<GameController> savedGames;
    private Canvas canvas;
    GraphicsContext graphics_context;

    @FXML
    private Label gameHighScore;

    @FXML
    private ImageView background;

    @FXML
    private Button setting;

    public GameController(){
        game = this;
        currentScore = 0;
        gameObjects = new ArrayList<>();
        savedGames = new ArrayList<>();
        lastSavedGameIndex = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedHighScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            score = (HighScore)in.readObject();
            in.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
            score.setHighScore(-1);
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            score.setHighScore(-1);
        }

        try {
            FileInputStream file = new FileInputStream("serial/SerializedCurrentGame.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            currentGame = (GameController)in.readObject();
            in.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        gameHighScore.setText(" " + score.getHighScore());

        backgroundAnimation = new TranslateTransition();
        backgroundAnimation.setNode(background);
        backgroundAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        backgroundAnimation.setDuration(Duration.millis(50000));
        backgroundAnimation.setByX(-200);
        backgroundAnimation.play();
    }

    public void serializeHighScore(int currentScore){
        HighScore score = new HighScore();
        score.setHighScore(currentScore);
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedHighScore.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(score);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public void settings(ActionEvent event, Stage oldStage) throws IOException{
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedCurrentGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(currentGame);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        root = loader.load();
        stage = new Stage();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(oldStage);
        stage.showAndWait();
    }

    public int getScore(){
        return this.currentScore;
    }

    public void setScore(int _score){
        this.currentScore = _score;
    }

    public void startGame(Stage _stage)throws IOException{
        stage = _stage;
        canvas = new Canvas(720, 480);
        graphics_context = canvas.getGraphicsContext2D();
        Image backgroundImage = new Image(this.getClass().getResource("/assets/BackgroundPerfect.png").toString());
        graphics_context.drawImage(backgroundImage, 0, 0);
        Button settingsButton = new Button();
        settingsButton.setTranslateY(10);
        settingsButton.setTranslateX(10);
        settingsButton.setMinWidth(50);
        settingsButton.setMinHeight(50);
        settingsButton.setId("setting");
        settingsButton.setOnAction(e -> {
            try {
                game.settings(new ActionEvent(), stage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Group group = new Group(canvas);
        group.getChildren().add(settingsButton);
        scene = new Scene(group);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            e.consume();
            exitGame();
        });
        stage.show();
    }

    public void exitGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        Stage st = (Stage)alert.getDialogPane().getScene().getWindow();
        st.getIcons().add(new Image(this.getClass().getResource("/assets/logo.png").toString()));
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Are you sure you want to exit?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}
