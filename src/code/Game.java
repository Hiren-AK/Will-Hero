package code;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Game {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    public Game(Stage stage){
        this.stage = stage;
    }


    public void startGame() throws IOException {
        loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        root = loader.load();
        stage.setTitle("Will Hero");
        Image icon = new Image("/assets/logo.png");
        Scene scene = new Scene(root);
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
