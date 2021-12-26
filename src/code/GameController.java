package code;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;
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
    private Timeline timeline;

    @FXML
    private Label gameHighScore;

    @FXML
    private ImageView background;

    @FXML
    private Button setting;

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
            System.out.println("ClassNotFoundException" + " is caught");
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
            System.out.println("ClassNotFoundException" + " is caught");
        }
        gameHighScore.setText(" " + score.getHighScore());

        backgroundAnimation = new TranslateTransition();
        backgroundAnimation.setNode(background);
        backgroundAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        backgroundAnimation.setDuration(Duration.millis(50000));
        backgroundAnimation.setByX(-200);
        backgroundAnimation.play();
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
}
