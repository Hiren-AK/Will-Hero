package code;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
    private Game currentGame = new Game();
    private Score gameScoreCount = new Score(0);
    private AnimationTimer animationTimer;
    private boolean mouseClicked = false;
    private boolean noIsland = false;

    @FXML
    private Label gameHighScore;
    @FXML
    private Label gameScore;
    @FXML
    private Label coinScore;
    @FXML
    private Button setting;
    @FXML
    private Button clickToPlay;
    @FXML
    private AnchorPane startAnchorPane;
    @FXML
    private ImageView highScoreText;
    @FXML
    private ImageView coinScoreImage;
    @FXML
    private ImageView background;
    @FXML
    private Rectangle queenRectangle;
    @FXML
    private Rectangle kingRectangle;
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
    private Rectangle islandRectangle7;
    @FXML
    private Rectangle islandRectangle8;
    @FXML
    private Rectangle islandRectangle9;
    @FXML
    private Rectangle islandRectangle10;
    @FXML
    private Rectangle islandRectangle11;
    @FXML
    private Rectangle islandRectangle12;

    @FXML
    private Rectangle coin1;
    @FXML
    private Rectangle coin2;
    @FXML
    private Rectangle coin3;
    @FXML
    private Rectangle coin4;
    @FXML
    private Rectangle coin5;
    @FXML
    private Rectangle coin6;
    @FXML
    private Rectangle coin7;
    @FXML
    private Rectangle coin8;
    @FXML
    private Rectangle coin9;
    @FXML
    private Rectangle coin10;

    @FXML
    private  Rectangle greenOrc1;
    @FXML
    private  Rectangle greenOrc2;
    @FXML
    private  Rectangle greenOrc3;
    @FXML
    private  Rectangle greenOrc4;
    @FXML
    private  Rectangle greenOrc5;

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
        rectangleSetter(queenRectangle, "/assets/Queen.png" );
        rectangleSetter(kingRectangle, "/assets/King.png");
        rectangleSetter(islandRectangle1, "/assets/Island1.png");
        rectangleSetter(islandRectangle2, "/assets/Island2.png");
        rectangleSetter(islandRectangle3, "/assets/Island3.png");
        rectangleSetter(islandRectangle4, "/assets/Island4.png");
        rectangleSetter(islandRectangle5, "/assets/Island5.png");
        rectangleSetter(islandRectangle6, "/assets/Island6.png");
        rectangleSetter(islandRectangle7, "/assets/Island1.png");
        rectangleSetter(islandRectangle8, "/assets/Island4.png");
        rectangleSetter(islandRectangle9, "/assets/Island3.png");
        rectangleSetter(islandRectangle10, "/assets/Island5.png");
        rectangleSetter(islandRectangle11, "/assets/Island6.png");
        rectangleSetter(islandRectangle12, "/assets/Island2.png");

        rectangleSetter(greenOrc1, "/assets/GreenOrc.png");
        rectangleSetter(greenOrc2, "/assets/GreenOrc.png");
        rectangleSetter(greenOrc3, "/assets/GreenOrc.png");
        rectangleSetter(greenOrc4, "/assets/GreenOrc.png");
        rectangleSetter(greenOrc5, "/assets/GreenOrc.png");

        coinSetter(coin1);
        coinSetter(coin2);
        coinSetter(coin3);
        coinSetter(coin4);
        coinSetter(coin5);
        coinSetter(coin6);
        coinSetter(coin7);
        coinSetter(coin8);
        coinSetter(coin9);
        coinSetter(coin10);

        gameScore.setText(" " + gameScoreCount.getScore());

        animationTimer = new AnimationTimer() {

            double myTime = 0.0;
            int dir = 1;
            double velocityY = 0;
            double damp = 0.7;
            double gravity = 9.8;
            double previousVelocity = 0;


            @Override
            public void handle(long l) {
                double currentY = queenRectangle.getLayoutY();
                double newY = currentY;
                if(currentY > 400){
                    myTime = 0.13;
                }
                if(mouseClicked){
                    velocityY = 0;
                    myTime = 0;
                    newY = currentY + velocityY;
                    mouseClicked = false;
                }
                else{
                    velocityY += gravity * 0.5 * myTime * myTime;
                    //velocityY = -5;
                    newY = currentY + velocityY;
                }
                queenRectangle.relocate(queenRectangle.getLayoutX(), newY);
                previousVelocity = velocityY;
                myTime+=0.001;
            }

        };
    }

    public void placeGameObjects(){
        resumeGame(50);
        clickToPlay.setOpacity(0);
        clickToPlay.setDisable(true);
        queenRectangle.setOnMouseClicked(mouseEvent -> moveForward());
        animationTimer.start();
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
        queenRectangle.setTranslateX(queenRectangle.getTranslateX() + 20);
        gameHighScore.setTranslateX(gameHighScore.getTranslateX() + 20);
        highScoreText.setTranslateX(highScoreText.getTranslateX() + 20);
        coinScoreImage.setTranslateX(coinScoreImage.getTranslateX() + 20);
        gameScore.setTranslateX(gameScore.getTranslateX()+20);
        setting.setTranslateX(setting.getTranslateX() + 20);
        coinScore.setTranslateX(coinScore.getTranslateX() + 20);
        background.setTranslateX(background.getTranslateX()-10);
        startAnchorPane.setTranslateX(startAnchorPane.getTranslateX()-20);
        gameScoreCount.setScore(gameScoreCount.getScore()+1);
        if(gameScoreCount.getScore() > score.getHighScore()){
            score.setHighScore(gameScoreCount.getScore());
            serializeHighScore(score.getHighScore());
        }
        gameScore.setText(" "+gameScoreCount.getScore());
        serializeScore(gameScoreCount);
        try {
            if (gameScoreCount.getScore() >= 141) {
                quitGame();
            }
        }
        catch(IOException ex){
            System.out.println("IOException is caught");
        }
        mouseClicked = true;
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

    public void rectangleSetter(Rectangle gameObject, String name){
        Image gameObjectImage = new Image(this.getClass().getResource(name).toString());
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
    }

    public void coinSetter(Rectangle gameObject){
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/Coin.png").toString());
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
    }

    public void resumeGame(int resumeScore){
        queenRectangle.setTranslateX(queenRectangle.getTranslateX() + (resumeScore*20));
        gameHighScore.setTranslateX(gameHighScore.getTranslateX() + (resumeScore*20));
        highScoreText.setTranslateX(highScoreText.getTranslateX() + (resumeScore*20));
        coinScoreImage.setTranslateX(coinScoreImage.getTranslateX() + (resumeScore*20));
        gameScore.setTranslateX(gameScore.getTranslateX() + (resumeScore*20));
        setting.setTranslateX(setting.getTranslateX() + (resumeScore*20));
        coinScore.setTranslateX(coinScore.getTranslateX() + (resumeScore*20));
        background.setTranslateX(background.getTranslateX() - (resumeScore*25));
        startAnchorPane.setTranslateX(startAnchorPane.getTranslateX() - (resumeScore*20));
        gameScoreCount.setScore(gameScoreCount.getScore() + resumeScore);
        if(gameScoreCount.getScore() > score.getHighScore()){
            score.setHighScore(gameScoreCount.getScore());
            serializeHighScore(score.getHighScore());
        }
        gameScore.setText(" "+gameScoreCount.getScore());
        serializeScore(gameScoreCount);
    }

    public void quitGame() throws IOException{
        loader = new FXMLLoader(getClass().getResource("Endgame.fxml"));
        root = loader.load();
        //stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage = (Stage) clickToPlay.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}