package code;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
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
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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
    public static AnimationTimer tntTimer;
    private boolean gameEnd = false;
    private boolean revive = false;
    private boolean weaponized = false;

    Bounds queenBounds;

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
    @FXML
    private Rectangle blastTNT;
    @FXML
    private Rectangle weaponRect;

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
        GameObjects queen = new Queen(queenRectangle);
        GameObjects king = new King(kingRectangle);

        GameObjects island1 = new Island(islandRectangle1,"/assets/Island1.png", islandList);
        GameObjects island2 = new Island(islandRectangle2, "/assets/Island2.png", islandList);
        GameObjects island3 = new Island(islandRectangle3, "/assets/Island3.png", islandList);
        GameObjects island4 = new Island(islandRectangle4, "/assets/Island4.png", islandList);
        GameObjects island5 = new Island(islandRectangle5, "/assets/Island5.png", islandList);
        GameObjects island6 = new Island(islandRectangle6, "/assets/Island6.png", islandList);
        GameObjects island7 = new Island(islandRectangle7, "/assets/Island1.png", islandList);
        GameObjects island8 = new Island(islandRectangle8, "/assets/Island4.png", islandList);
        GameObjects island9 = new Island(islandRectangle9, "/assets/Island3.png", islandList);
        GameObjects island10 = new Island(islandRectangle10, "/assets/Island5.png", islandList);

        GameObjects GreenOrc1 = new GreenOrc(greenOrc1, orcListG);
        GameObjects GreenOrc2 = new GreenOrc(greenOrc2, orcListG);
        GameObjects GreenOrc3 = new GreenOrc(greenOrc3, orcListG);
        GameObjects GreenOrc4 = new GreenOrc(greenOrc4, orcListG);
        GameObjects GreenOrc5 = new GreenOrc(greenOrc5, orcListG);

        GameObjects RedOrc1 = new RedOrc(redOrc1, orcListR);
        GameObjects RedOrc2 = new RedOrc(redOrc2, orcListR);
        GameObjects RedOrc3 = new RedOrc(redOrc3, orcListR);

        GameObjects BossOrc = new Boss(boss, orcListB);

        GameObjects Treasure1 = new Treasure(treasure1, treasureList);
        GameObjects Treasure2 = new Treasure(treasure2, treasureList);
        GameObjects Treasure3 = new Treasure(treasure3, treasureList);

        GameObjects TNT1 = new bombTNT(TNT);
        rectangleSetter(blastTNT, "/assets/blastTNT.png");
        blastTNT.setOpacity(0);

        GameObjects Coin1 = new Coin(coin1, coinList);
        GameObjects Coin2 = new Coin(coin2, coinList);
        GameObjects Coin3 = new Coin(coin3, coinList);
        GameObjects Coin4 = new Coin(coin4, coinList);
        GameObjects Coin5 = new Coin(coin5, coinList);
        GameObjects Coin6 = new Coin(coin6, coinList);
        GameObjects Coin7 = new Coin(coin7, coinList);
        GameObjects Coin8 = new Coin(coin8, coinList);
        GameObjects Coin9 = new Coin(coin9, coinList);
        GameObjects Coin10 = new Coin(coin10, coinList);
        GameObjects Coin11 = new Coin(coin11, coinList);
        GameObjects Coin12 = new Coin(coin12, coinList);
        GameObjects Coin13 = new Coin(coin13, coinList);
        GameObjects Coin14 = new Coin(coin14, coinList);
        GameObjects Coin15 = new Coin(coin15, coinList);
        GameObjects Coin16 = new Coin(coin16, coinList);
        GameObjects Coin17 = new Coin(coin17, coinList);
        GameObjects Coin18 = new Coin(coin18, coinList);
        GameObjects Coinx1 = new Coin(coinx1, coinList);
        GameObjects Coinx2 = new Coin(coinx2, coinList);
        GameObjects Coinx3 = new Coin(coinx3, coinList);
        GameObjects Coinx4 = new Coin(coinx4, coinList);
        GameObjects Coinx5 = new Coin(coinx5, coinList);
        GameObjects Coinx6 = new Coin(coinx6, coinList);
        GameObjects Coinx7 = new Coin(coinx7, coinList);
        GameObjects Coinx8 = new Coin(coinx8, coinList);
        GameObjects Coinx9 = new Coin(coinx9, coinList);
        GameObjects Coinx10 = new Coin(coinx10, coinList);
        GameObjects Coinx11 = new Coin(coinx11, coinList);
        GameObjects Coinx12 = new Coin(coinx12, coinList);
        GameObjects Coinx13 = new Coin(coinx13, coinList);
        GameObjects Coinx14 = new Coin(coinx14, coinList);
        GameObjects Coinx15 = new Coin(coinx15, coinList);
        GameObjects Coinx16 = new Coin(coinx16, coinList);

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
                    queenBounds = new BoundingBox(queenBounds.getMinX(), queenBounds.getMinY(), queenBounds.getMinZ(), queenBounds.getWidth()-30, queenBounds.getHeight()-7, queenBounds.getDepth());
                } else {
                    velocityY += gravity * 0.5 * animTime * animTime;
                    newY = currentY + velocityY;
                }
                for (int i = 0; i < islandList.size(); i++) {
                    queenBounds = queenRectangle.getBoundsInParent();
                    queenBounds = new BoundingBox(queenBounds.getMinX(), queenBounds.getMinY(), queenBounds.getMinZ(), queenBounds.getWidth()-30, queenBounds.getHeight()-7, queenBounds.getDepth());

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

                for(int i = 0; i < treasureList.size(); i++){
                    Bounds treasureBounds = treasureList.get(i).getBoundsInParent();
                    if(queenRectangle.getBoundsInParent().intersects(treasureBounds) && i != 1){
                        treasureOpenerCoins(treasureList.get(i));
                        Rectangle dummyTreasure = new Rectangle();
                        treasureList.set(i, dummyTreasure);
                    }
                    else if(queenRectangle.getBoundsInParent().intersects(treasureBounds)){
                        treasureOpenerWeapon(treasureList.get(i));
                        Queen q = (Queen) queen;
                        q.Weaponize();
                        Rectangle dummyTreasure = new Rectangle();
                        treasureList.set(i, dummyTreasure);
                    }
                }

                for(int i = 0; i < orcListR.size(); i++){
                    Bounds orcBounds = orcListR.get(i).getBoundsInParent();
                    orcBounds = new BoundingBox(orcBounds.getMinX()+30, orcBounds.getMinY(), orcBounds.getMinZ(), orcBounds.getWidth()-50, orcBounds.getHeight()-5, orcBounds.getDepth());
                    if(queenRectangle.getBoundsInParent().intersects(orcBounds) && !weaponized){
                        try {
                            if(coinCount >= 100){
                                revive = true;
                            }
                            gameScoreCount.setScore(gameScoreCount.getScore()+10);
                            quitGame();
                        }
                        catch(IOException ex){
                            System.out.println("IOException is caught");
                        }
                    }
                    else if(queenRectangle.getBoundsInParent().intersects(orcBounds)){
                        orcListR.get(i).setOpacity(0);
                    }
                }

                Bounds orcBounds = boss.getBoundsInParent();
                orcBounds = new BoundingBox(orcBounds.getMinX()+30, orcBounds.getMinY(), orcBounds.getMinZ(), orcBounds.getWidth()-60, orcBounds.getHeight()-5, orcBounds.getDepth());
                if(queenRectangle.getBoundsInParent().intersects(orcBounds)){
                    try {
                        if(coinCount >= 100) revive = true;
                        gameScoreCount.setScore(gameScoreCount.getScore()-7);
                        quitGame();
                    }
                    catch(IOException ex){
                        System.out.println("IOException is caught");
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

        tntTimer = new AnimationTimer() {
            double tntTime = 0.0;
            double velocityYtnt = 0;
            double gravitytnt = 9.8;
            double previousVelocitytnt = 0;

            @Override
            public void handle(long l) {
                double currentYtnt = TNT.getLayoutY();
                double newY = currentYtnt;
                velocityYtnt += gravitytnt * 0.5 * tntTime * tntTime;
                newY = currentYtnt + velocityYtnt;
                for (int i = 0; i < islandList.size(); i++) {
                    Bounds tntBounds = TNT.getBoundsInParent();
                    tntBounds = new BoundingBox(tntBounds.getMinX(), tntBounds.getMinY(), tntBounds.getMinZ(), tntBounds.getWidth()-30, tntBounds.getHeight()-5, tntBounds.getDepth());

                    Bounds islandBounds = islandList.get(i).getBoundsInParent();
                    if (tntBounds.intersects(islandBounds)) {
                        velocityYtnt = -1;
                        newY = currentYtnt + velocityYtnt;
                    }
                }
                if(tntTime >= 0.19){
                    blastTNT.setOpacity(100);
                    TNT.setOpacity(0);
                }
                if(tntTime >= 0.2){
                    tntBlast();
                }
                tntTime += 0.001;
                TNT.relocate(TNT.getLayoutX(), newY);
                previousVelocitytnt = velocityYtnt;
            }
        };

        TranslateTransition translateRed1 = new TranslateTransition();
        translateRed1.setNode(redOrc1);
        translateRed1.setCycleCount(TranslateTransition.INDEFINITE);
        translateRed1.setDuration(Duration.millis(3500));
        translateRed1.setByY(-100);
        translateRed1.setAutoReverse(true);
        translateRed1.play();

        TranslateTransition translateRed2 = new TranslateTransition();
        translateRed2.setNode(redOrc2);
        translateRed2.setCycleCount(TranslateTransition.INDEFINITE);
        translateRed2.setDuration(Duration.millis(3500));
        translateRed2.setByY(-100);
        translateRed2.setAutoReverse(true);
        translateRed2.play();

        TranslateTransition translateRed3 = new TranslateTransition();
        translateRed3.setNode(redOrc3);
        translateRed3.setCycleCount(TranslateTransition.INDEFINITE);
        translateRed3.setDuration(Duration.millis(3500));
        translateRed3.setByY(-120);
        translateRed3.setAutoReverse(true);
        translateRed3.play();

        TranslateTransition translateBoss = new TranslateTransition();
        translateBoss.setNode(boss);
        translateBoss.setCycleCount(TranslateTransition.INDEFINITE);
        translateBoss.setDuration(Duration.millis(3000));
        translateBoss.setByY(-120);
        translateBoss.setAutoReverse(true);
        translateBoss.play();
    }

    public void tntBlast(){
        tntTimer.stop();
        Bounds tntBlastBounds = blastTNT.getBoundsInParent();
        tntBlastBounds = new BoundingBox(tntBlastBounds.getMinX()-10, tntBlastBounds.getMinY()-20, tntBlastBounds.getMinZ(), tntBlastBounds.getWidth()+20, tntBlastBounds.getHeight()+20, tntBlastBounds.getDepth());

        if(tntBlastBounds.intersects(queenRectangle.getBoundsInParent())){
            revive = true;
            try{
                quitGame();
            }
            catch(Exception e){
                //
            }
        }
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
        queenBounds = new BoundingBox(queenBounds.getMinX(), queenBounds.getMinY(), queenBounds.getMinZ(), queenBounds.getWidth()-40, queenBounds.getHeight()-7, queenBounds.getDepth());
        gameHighScore.setTranslateX(gameHighScore.getTranslateX() + 20);
       highScoreText.setTranslateX(highScoreText.getTranslateX() + 20);
        coinScoreImage.setTranslateX(coinScoreImage.getTranslateX() + 20);
        gameScore.setTranslateX(gameScore.getTranslateX()+20);
        setting.setTranslateX(setting.getTranslateX() + 20);
        coinScore.setTranslateX(coinScore.getTranslateX() + 20);
        background.setTranslateX(background.getTranslateX()-5);
        startAnchorPane.setTranslateX(startAnchorPane.getTranslateX()-20);
        gameScoreCount.setScore(gameScoreCount.getScore()+1);
        coinScore.setText(" "+coinCount);
        serializeScore(gameScoreCount);

        for(int i=0; i < orcListG.size(); i++){
            Bounds orcBounds = orcListG.get(i).getBoundsInParent();
            orcBounds = new BoundingBox(orcBounds.getMinX(), orcBounds.getMinY(), orcBounds.getMinZ(), orcBounds.getWidth(), orcBounds.getHeight()-5, orcBounds.getDepth());
            if(queenBounds.intersects(orcBounds)){
                greenOrc(orcListG.get(i));
            }
        }

        if(queenBounds.intersects(TNT.getBoundsInParent())){
            tntTimer.start();
        }

        coinScoreCount.setCoinScore(coinCount);
        serializeCoinScore(coinScoreCount);
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

        if(gameScoreCount.getScore() > score.getHighScore()){
            score.setHighScore(gameScoreCount.getScore());
            serializeHighScore(score.getHighScore());
        }
        gameScore.setText(" "+gameScoreCount.getScore());
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
                if(coinCount > 0){
                    coinCount -= 1;
                }
            }
        }
        if(collide == false){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(gameObject);
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

    public void treasureOpenerCoins(Rectangle gameObject){
        coinCount += 10;
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/OpenTreasure.png").toString());
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
    }

    public void treasureOpenerWeapon(Rectangle gameObject){
        weaponized = true;
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/OpenTreasure.png").toString());
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
        stage = (Stage) setting.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}