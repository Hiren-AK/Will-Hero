package code;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static code.GameController.resumeAnimations;

public class ReviveController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;
    private  int coinCount;
    private CoinScore coinScoreCount = new CoinScore(0);

    @FXML
    private Label coinScoreText;
    private Score gameScoreCount;
    private Score currentGameScore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CoinScore coinScoreCount = new CoinScore(0);
        try {
            FileInputStream file = new FileInputStream("serial/SerializedCoinScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            coinScoreCount = (CoinScore)in.readObject();
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
        coinScoreText.setText(""+coinCount);
    }


    public void revive(ActionEvent event) throws IOException {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedCoinScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            coinScoreCount = (CoinScore)in.readObject();
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
        coinScoreCount.setCoinScore(coinScoreCount.getCoinScore() - 100);
        serializeCoinScore(coinScoreCount);
        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            currentGameScore = (Score)in.readObject();
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
            FileOutputStream file = new FileOutputStream("serial/SerializedCurrentGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(currentGameScore);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

        loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage = (Stage)stage.getOwner();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            e.consume();
            exitGame();
        });
        stage.show();
    }

    public void quitGame(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("Endgame.fxml"));
        root = loader.load();
        stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
        ((Node)event.getSource()).getScene().getWindow().hide();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
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
}