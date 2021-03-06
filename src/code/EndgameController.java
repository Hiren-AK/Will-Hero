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

import java.io.File;
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
    private Score currentScore = new Score(0);

    @FXML
    private Label endgameHighScore;

    @FXML
    private Label endgameScore;

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

        try {
            FileInputStream file = new FileInputStream("serial/SerializedScore.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            currentScore = (Score) in.readObject();
            in.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
            currentScore.setScore(0);
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            currentScore.setScore(0);
        }
        endgameScore.setText(" " + currentScore.getScore());

        Score slotScore = new Score(-1);
        try {
            FileInputStream file = new FileInputStream("serial/SerializedSlot.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            slotScore = (Score) in.readObject();
            in.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
            slotScore.setScore(-1);
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            slotScore.setScore(-1);
        }

        if(slotScore.getScore() == 1){
            File file1 = new File("serial/SerializedGame1.txt");
            file1.delete();
        }
        if(slotScore.getScore() == 2){
            File file1 = new File("serial/SerializedGame2.txt");
            file1.delete();
        }
        if(slotScore.getScore() == 3){
            File file1 = new File("serial/SerializedGame3.txt");
            file1.delete();
        }
        slotScore.setScore(0);
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