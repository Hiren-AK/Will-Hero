package code;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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

        gameScore.setText(" " + gameScoreCount.getScore());
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

    public void placeGameObjects(){
        resumeGame(50);
        clickToPlay.setOpacity(0);
        clickToPlay.setDisable(true);
        //heroRectangle.setOnMouseClicked(mouseEvent -> moveForward());
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
        startAnchorPane.setTranslateX(startAnchorPane.getTranslateX()-20);
        gameScoreCount.setScore(gameScoreCount.getScore()+1);
        if(gameScoreCount.getScore() > score.getHighScore()){
            score.setHighScore(gameScoreCount.getScore());
            serializeHighScore(score.getHighScore());
        }
        gameScore.setText(" "+gameScoreCount.getScore());
        serializeScore(gameScoreCount);
        try {
            if (gameScoreCount.getScore() >= 135) {
                quitGame();
            }
        }
        catch(IOException ex){
            System.out.println("IOException is caught");
        }
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

    public void resumeGame(int resumeScore){
        queenRectangle.setTranslateX(queenRectangle.getTranslateX() + (resumeScore*20));
        gameHighScore.setTranslateX(gameHighScore.getTranslateX() + (resumeScore*20));
        highScoreText.setTranslateX(highScoreText.getTranslateX() + (resumeScore*20));
        coinScoreImage.setTranslateX(coinScoreImage.getTranslateX() + (resumeScore*20));
        gameScore.setTranslateX(gameScore.getTranslateX() + (resumeScore*20));
        setting.setTranslateX(setting.getTranslateX() + (resumeScore*20));
        coinScore.setTranslateX(coinScore.getTranslateX() + (resumeScore*20));
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