package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    public void resumeGameFromSettings(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
    }

    public void saveAndQuit(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("Endgame.fxml"));
        root = loader.load();
        stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
        ((Node)event.getSource()).getScene().getWindow().hide();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
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
}