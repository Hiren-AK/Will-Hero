package code;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
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
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private ArrayList<Rectangle> islandList = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> coinList = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> orcListG = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> orcListR = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> orcListB = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> treasureList = new ArrayList<Rectangle>();

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    private HighScore score = new HighScore();
    private CoinScore coinScoreCount = new CoinScore(0);
    private int coinCount;
    private Score gameScoreCount = new Score(0);
    private boolean mouseClicked = false;
    public static AnimationTimer animationTimer;
    public static AnimationTimer greenOrcTimer;
    private boolean gameEnd = false;
    private boolean revive = false;

    Bounds queenBounds, kingBounds;

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
    private Rectangle coin11;
    @FXML
    private Rectangle coin12;
    @FXML
    private Rectangle coin13;
    @FXML
    private Rectangle coin14;
    @FXML
    private Rectangle coin15;
    @FXML
    private Rectangle coin16;
    @FXML
    private Rectangle coin17;
    @FXML
    private Rectangle coin18;
    @FXML
    private Rectangle coinx1;
    @FXML
    private Rectangle coinx2;
    @FXML
    private Rectangle coinx3;
    @FXML
    private Rectangle coinx4;
    @FXML
    private Rectangle coinx5;
    @FXML
    private Rectangle coinx6;
    @FXML
    private Rectangle coinx7;
    @FXML
    private Rectangle coinx8;
    @FXML
    private Rectangle coinx9;
    @FXML
    private Rectangle coinx10;
    @FXML
    private Rectangle coinx11;
    @FXML
    private Rectangle coinx12;
    @FXML
    private Rectangle coinx13;
    @FXML
    private Rectangle coinx14;
    @FXML
    private Rectangle coinx15;
    @FXML
    private Rectangle coinx16;

    @FXML
    private  Rectangle redOrc1;
    @FXML
    private  Rectangle redOrc2;
    @FXML
    private  Rectangle redOrc3;

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

    @FXML
    private Rectangle boss;

    @FXML
    private Rectangle treasure1;
    @FXML
    private Rectangle treasure2;
    @FXML
    private Rectangle treasure3;
    @FXML
    private Rectangle TNT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedHighScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            score = (HighScore) in.readObject();
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

        gameHighScore.setText(" " + score.getHighScore());

        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            gameScoreCount = (Score)in.readObject();
            in.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        try {
            FileInputStream file = new FileInputStream("serial/SerializedCoinScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            coinScoreCount = (CoinScore) in.readObject();
            coinCount = coinScoreCount.getCoinScore();
            in.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        coinScore.setText(" " + coinScoreCount.getCoinScore());
        if (coinScoreCount.getCoinScore() >= 100) {
            revive = true;
        }
        rectangleSetter(queenRectangle, "/assets/Queen.png");
        rectangleSetter(kingRectangle, "/assets/King.png");

        islandSetter(islandRectangle1, "/assets/Island1.png");
        islandSetter(islandRectangle2, "/assets/Island2.png");
        islandSetter(islandRectangle3, "/assets/Island3.png");
        islandSetter(islandRectangle4, "/assets/Island4.png");
        islandSetter(islandRectangle5, "/assets/Island5.png");
        islandSetter(islandRectangle6, "/assets/Island6.png");
        islandSetter(islandRectangle7, "/assets/Island1.png");
        islandSetter(islandRectangle8, "/assets/Island4.png");
        islandSetter(islandRectangle9, "/assets/Island3.png");
        islandSetter(islandRectangle10, "/assets/Island5.png");

        orcSetter(greenOrc1, "/assets/GreenOrc.png", 0);
        orcSetter(greenOrc2, "/assets/GreenOrc.png", 0);
        orcSetter(greenOrc3, "/assets/GreenOrc.png", 0);
        orcSetter(greenOrc4, "/assets/GreenOrc.png", 0);
        orcSetter(greenOrc5, "/assets/GreenOrc.png", 0);

        orcSetter(redOrc1, "/assets/RedOrc.png", 1);
        orcSetter(redOrc2, "/assets/RedOrc.png", 1);
        orcSetter(redOrc3, "/assets/RedOrc.png", 1);

        orcSetter(boss, "/assets/BossOrc.png", 2);

        treasureSetter(treasure1, "/assets/ClosedTreasure.png");
        treasureSetter(treasure2, "/assets/ClosedTreasure.png");
        treasureSetter(treasure3, "/assets/ClosedTreasure.png");

        rectangleSetter(TNT, "/assets/TNT.png");

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
        coinSetter(coin11);
        coinSetter(coin12);
        coinSetter(coin13);
        coinSetter(coin14);
        coinSetter(coin15);
        coinSetter(coin16);
        coinSetter(coin17);
        coinSetter(coin18);
        coinSetter(coinx1);
        coinSetter(coinx2);
        coinSetter(coinx3);
        coinSetter(coinx4);
        coinSetter(coinx5);
        coinSetter(coinx6);
        coinSetter(coinx7);
        coinSetter(coinx8);
        coinSetter(coinx9);
        coinSetter(coinx10);
        coinSetter(coinx11);
        coinSetter(coinx12);
        coinSetter(coinx13);
        coinSetter(coinx14);
        coinSetter(coinx15);
        coinSetter(coinx16);

        gameScore.setText(" " + gameScoreCount.getScore());

        animationTimer = new AnimationTimer() {
            double animTime = 0.0;
            double velocityY = 0;
            double gravity = 9.8;
            double previousVelocity = 0;

            @Override
            public void handle(long l) {
                double currentY = queenRectangle.getLayoutY();
                double newY = currentY;
                if (currentY > 400) {
                    animTime = 0.13;
                }
                if (mouseClicked) {
                    velocityY = 0;
                    animTime = 0;
                    newY = currentY + velocityY;
                    mouseClicked = false;
                    queenBounds = queenRectangle.getBoundsInParent();
                } else {
                    velocityY += gravity * 0.5 * animTime * animTime;
                    newY = currentY + velocityY;
                }
                for (int i = 0; i < islandList.size(); i++) {
                    queenBounds = queenRectangle.getBoundsInParent();
                    Bounds islandBounds = islandList.get(i).getBoundsInParent();
                    if (queenBounds.intersects(islandBounds)) {
                        velocityY = -1;
                        animTime = 0;
                        newY = currentY + velocityY;
                    }
                }
                for (int i = 0; i < coinList.size(); i++) {
                    Bounds coinBounds = coinList.get(i).getBoundsInParent();
                    if (queenBounds.intersects(coinBounds)) {
                        coinCount++;
                        coinList.get(i).setOpacity(0);
                        Rectangle dummyCoin = new Rectangle();
                        coinList.set(i, dummyCoin);
                    }
                }
                queenRectangle.relocate(queenRectangle.getLayoutX(), newY);
                previousVelocity = velocityY;
                animTime += 0.001;
                if (gameEnd) {
                    this.stop();
                }
                try {
                    if (queenRectangle.getLayoutY() > 400) {
                        this.stop();
                        quitGame();
                    }
                } catch (IOException ex) {
                    System.out.println("IOException is caught");
                }
            }
        };
    }

    public void placeGameObjects(){
        resumeGame(gameScoreCount.getScore());
        clickToPlay.setOpacity(0);
        clickToPlay.setDisable(true);
        queenRectangle.setOnMouseClicked(mouseEvent -> moveForward());
        animationTimer.start();
    }

    public void settings(ActionEvent event) throws IOException{
        animationTimer.stop();
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(gameScoreCount);
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
        stage.initOwner(clickToPlay.getScene().getWindow());
        stage.showAndWait();
    }

    public void reviver() throws IOException{
        animationTimer.stop();
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(gameScoreCount);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        loader = new FXMLLoader(getClass().getResource("Revive.fxml"));
        root = loader.load();
        stage = new Stage();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner((Stage) clickToPlay.getScene().getWindow());
        stage.show();
    }

    public void moveForward(){
        queenRectangle.setTranslateX(queenRectangle.getTranslateX() + 20);
        queenBounds = queenRectangle.getBoundsInParent();
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
        coinScore.setText(" "+coinCount);
        serializeScore(gameScoreCount);
        try {
            if (gameScoreCount.getScore() >= 143) {
                revive = false;
                quitGame();
            }
        }
        catch(IOException ex){
            System.out.println("IOException is caught");
        }
        mouseClicked = true;
        for(int i=0; i < coinList.size(); i++){
            Bounds coinBounds = coinList.get(i).getBoundsInParent();
            if(queenBounds.intersects(coinBounds)){
                coinCount++;
                coinList.get(i).setOpacity(0);
                Rectangle dummyCoin = new Rectangle();
                coinList.set(i, dummyCoin);
            }
        }

        for(int i=0; i < orcListG.size(); i++){
            Bounds orcBounds = orcListG.get(i).getBoundsInParent();
            if(queenBounds.intersects(orcBounds)){
                greenOrc(orcListG.get(i));
            }
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

    public void serializeCoinScore(CoinScore score){
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedCoinScore.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(score);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public static void resumeAnimations(){
        animationTimer.start();
    }

    public void greenOrc(Rectangle gameObject){
        boolean collide = false;
        Bounds gameObjectBounds;
        gameObjectBounds = gameObject.getBoundsInParent();
        for (int i = 0; i < islandList.size(); i++) {

            Bounds islandBounds = islandList.get(i).getBoundsInParent();
            if (gameObjectBounds.intersects(islandBounds)){
                gameObject.setTranslateX(gameObject.getTranslateX() + 20);
                collide = true;
            }
        }
        if(collide == false){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(gameObject);
            translate.setCycleCount(TranslateTransition.INDEFINITE);
            translate.setDuration(Duration.millis(2500));
            translate.setByY(200);
            translate.play();
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
        coinList.add(gameObject);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameObject);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(2500));
        translate.setByY(-5);
        translate.setAutoReverse(true);
        translate.play();
    }

    public void orcSetter(Rectangle gameObject, String name, int id){
        Image gameObjectImage = new Image(this.getClass().getResource(name).toString());
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
        if(id == 0){
            orcListG.add(gameObject);
        }
        else if(id == 1){
            orcListR.add(gameObject);
        }
        else{
            orcListB.add(gameObject);
        }
    }

    public void islandSetter(Rectangle gameObject, String name){
        Image gameObjectImage = new Image(this.getClass().getResource(name).toString());
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
        islandList.add(gameObject);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameObject);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(2500));
        translate.setByY(-3);
        translate.setAutoReverse(true);
        translate.play();
    }

    public void treasureSetter(Rectangle gameObject, String name){
        Image gameObjectImage = new Image(this.getClass().getResource(name).toString());
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
        treasureList.add(gameObject);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameObject);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(2500));
        translate.setByY(-4);
        translate.setAutoReverse(true);
        translate.play();
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
        serializeScore(gameScoreCount);
    }

    public void quitGame() throws IOException{
        coinScoreCount.setCoinScore(coinCount);
        serializeCoinScore(coinScoreCount);
        if(revive == true){
            revive = false;
            reviver();
        }
        gameEnd = true;
        loader = new FXMLLoader(getClass().getResource("Endgame.fxml"));
        root = loader.load();
        stage = (Stage) clickToPlay.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}