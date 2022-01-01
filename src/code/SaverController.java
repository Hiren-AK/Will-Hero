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
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SaverController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;
    private Score game1;
    private Score game2;
    private Score game3;
    private Score currentGameScore;

    @FXML
    private Label saveSlot1;
    @FXML
    private Label saveSlot2;
    @FXML
    private Label saveSlot3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame1.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game1 = (Score) in.readObject();
            in.close();
            file.close();
            saveSlot1.setText("Score: " + game1.getScore());
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame2.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game2 = (Score) in.readObject();
            in.close();
            file.close();
            saveSlot2.setText("Score: " + game2.getScore());
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame3.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game3 = (Score) in.readObject();
            in.close();
            file.close();
            saveSlot3.setText("Score: " + game3.getScore());
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    public void returnHome(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
        stage.close();
        stage = (Stage)stage.getOwner();
        loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void endgame(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
    }

    public void saveInSlot1(ActionEvent event){
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
            FileOutputStream file = new FileOutputStream("serial/SerializedGame1.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(currentGameScore);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            returnHome(event);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void saveInSlot2(ActionEvent event){
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
            FileOutputStream file = new FileOutputStream("serial/SerializedGame2.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(currentGameScore);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

        try {
            returnHome(event);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void saveInSlot3(ActionEvent event){
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
            FileOutputStream file = new FileOutputStream("serial/SerializedGame3.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(currentGameScore);
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

        try {
            returnHome(event);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}