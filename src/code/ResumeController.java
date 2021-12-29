package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ResumeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;
    private Game game1;
    private Game game2;
    private Game game3;

    @FXML
    private Label loadSlot1;
    @FXML
    private Label loadSlot2;
    @FXML
    private Label loadSlot3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedGame1.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            game1 = (Game)in.readObject();
            in.close();
            file.close();
            loadSlot1.setText("Score: " + game1.getScore());
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
            game2 = (Game)in.readObject();
            in.close();
            file.close();
            loadSlot2.setText("Score: " + game2.getScore());
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
            game3 = (Game)in.readObject();
            in.close();
            file.close();
            loadSlot3.setText("Score: " + game3.getScore());
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
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
}