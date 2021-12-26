package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ResumeController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

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