package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SettingsController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    @FXML
    private Button saveExit;

    public void resumeGameFromSettings(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
    }

    public void saveAndQuit(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("Save.fxml"));
        root = loader.load();
        stage = new Stage();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(saveExit.getScene().getWindow());
        stage.showAndWait();
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