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

public class EndgameController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    private HighScore score = new HighScore();

    @FXML
    private Label endgameHighScore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileInputStream file = new FileInputStream("serial/SerializedHighScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            score = (HighScore)in.readObject();
            in.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
            score.setHighScore(-1);
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            score.setHighScore(-1);
        }
        endgameHighScore.setText(" " + score.getHighScore());
    }

    public void returnHomeFromEndgame(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}