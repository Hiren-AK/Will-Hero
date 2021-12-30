package code;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    private HighScore score = new HighScore();
    private Game currentGame;
    private TranslateTransition backgroundAnimation;
    private Score gameScoreCount = new Score(0);

    @FXML
    private Label gameHighScore;
    @FXML
    private ImageView background;
    @FXML
    private Button setting;
    @FXML
    private Rectangle heroRectangle;
    @FXML
    private Rectangle islandRectangle1;
    @FXML
    private Rectangle islandRectangle2;
    @FXML
    private Rectangle islandRectangle3;
    @FXML
    private Rectangle islandRectangle4;
    @FXML
    private Rectangle islandRectangle5;
    @FXML
    private Rectangle islandRectangle6;
    @FXML
    private AnchorPane startAnchorPane;
    @FXML
    private ImageView highScoreText;
    @FXML
    private Label gameScore;
    @FXML
    private ImageView coinScoreImage;
    @FXML
    private  Label coinScore;

    private Image heroImage = new Image(this.getClass().getResource("/assets/Queen.png").toString());
    private Image island1Image = new Image(this.getClass().getResource("/assets/Island1.png").toString());
    private Image island2Image = new Image(this.getClass().getResource("/assets/Island3.png").toString());
    private Image island3Image = new Image(this.getClass().getResource("/assets/Island4.png").toString());
    private Image island4Image = new Image(this.getClass().getResource("/assets/Island2.png").toString());
    private Image island5Image = new Image(this.getClass().getResource("/assets/Island6.png").toString());
    private Image island6Image = new Image(this.getClass().getResource("/assets/Island5.png").toString());


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
            currentGame = (Game)in.readObject();
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
        heroRectangle.setFill(new ImagePattern(heroImage));
        heroRectangle.setStrokeWidth(0);
        islandRectangle1.setFill((new ImagePattern(island1Image)));
        islandRectangle2.setFill((new ImagePattern(island2Image)));
        islandRectangle3.setFill((new ImagePattern(island3Image)));
        islandRectangle4.setFill((new ImagePattern(island4Image)));
        islandRectangle5.setFill((new ImagePattern(island5Image)));
        islandRectangle6.setFill((new ImagePattern(island6Image)));
        islandRectangle1.setStrokeWidth(0);
        islandRectangle2.setStrokeWidth(0);
        islandRectangle3.setStrokeWidth(0);
        islandRectangle4.setStrokeWidth(0);
        islandRectangle5.setStrokeWidth(0);
        islandRectangle6.setStrokeWidth(0);
        gameScore.setText(" " + gameScoreCount.getScore());
//        startAnchorPane.setClip(setting);
//        startAnchorPane.setClip(highScoreText);
//        startAnchorPane.setClip(gameHighScore);
        scene = startAnchorPane.getScene();
//        if(scene != null){
//            scene.setOnKeyPressed(keyEvent -> {
//                if(keyEvent.getCode() == KeyCode.W){
//                    moveForward();
//                    System.out.println("hi");
//                }
//            });
//        }
    }

    public void settings(ActionEvent event) throws IOException{
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
        stage.initOwner(setting.getScene().getWindow());
        stage.showAndWait();
    }

    public void moveForward(){
        heroRectangle.setTranslateX(heroRectangle.getTranslateX() + 20);
        setting.setTranslateX(setting.getTranslateX() + 20);
        gameHighScore.setTranslateX(gameHighScore.getTranslateX() + 20);
        highScoreText.setTranslateX(highScoreText.getTranslateX() + 20);
        startAnchorPane.setTranslateX(startAnchorPane.getTranslateX()-20);
        gameScore.setTranslateX(gameScore.getTranslateX()+20);
        gameScoreCount.setScore(gameScoreCount.getScore()+1);
        coinScoreImage.setTranslateX(coinScoreImage.getTranslateX() + 20);
        coinScore.setTranslateX(coinScore.getTranslateX() + 20);
        if(gameScoreCount.getScore() > score.getHighScore()){
            score.setHighScore(gameScoreCount.getScore());
            serializeHighScore(score.getHighScore());
        }
        gameScore.setText(" "+gameScoreCount.getScore());
        serializeScore(gameScoreCount);
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

    public void serializeScore(Score score){
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedScore.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(score);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}