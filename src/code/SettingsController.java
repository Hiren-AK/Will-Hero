package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static code.GameController.resumeAnimations;

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
        resumeAnimations();
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        Stage st = (Stage)alert.getDialogPane().getScene().getWindow();
        st.getIcons().add(new Image(this.getClass().getResource("/assets/logo.png").toString()));
        alert.setHeaderText("You are about to quit the current game");
        alert.setContentText("Are you sure you want to exit without saving?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage)((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner();
            ((Node)event.getSource()).getScene().getWindow().hide();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }
}