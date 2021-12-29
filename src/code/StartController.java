package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    private HighScore score = new HighScore();
    private GameController game;

    @FXML
    private Label startPageHighScore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new GameController();
        game.setScore(420);
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedCurrentGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

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
        startPageHighScore.setText(" " + score.getHighScore());
    }

    public void resumeSavedGame(ActionEvent event)throws IOException {
        loader = new FXMLLoader(getClass().getResource("Resume.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void playGame(ActionEvent event)throws IOException {
        loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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