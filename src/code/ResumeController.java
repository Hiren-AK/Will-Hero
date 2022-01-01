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
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ResumeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;
    private Score game1;
    private Score game2;
    private Score game3;

    @FXML
    private Label loadSlot1;
    @FXML
    private Label loadSlot2;
    @FXML
    private Label loadSlot3;
    @FXML
    private Button load1;
    @FXML
    private Button load2;
    @FXML
    private Button load3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame1.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game1 = (Score)in.readObject();
            in.close();
            file.close();
            loadSlot1.setText("Score: " + game1.getScore());
        }

        catch (IOException ex) {
            load1.setDisable(true);
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame2.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game2 = (Score)in.readObject();
            in.close();
            file.close();
            loadSlot2.setText("Score: " + game2.getScore());
        }

        catch (IOException ex) {
            load2.setDisable(true);
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame3.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game3 = (Score)in.readObject();
            in.close();
            file.close();
            loadSlot3.setText("Score: " + game3.getScore());
        }

        catch (IOException ex) {
            load3.setDisable(true);
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    public void returnHomeFromResumeSavedGames(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void load1(ActionEvent event) throws IOException {
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game1);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedCurrentGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game1);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
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

    public void load2(ActionEvent event) throws IOException {
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game2);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedCurrentGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game2);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
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

    public void load3(ActionEvent event) throws IOException {
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game3);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedCurrentGame.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game3);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
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
