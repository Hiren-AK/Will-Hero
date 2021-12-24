package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Group root;

    public void resumeGame(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("Resume1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void returnHome(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}