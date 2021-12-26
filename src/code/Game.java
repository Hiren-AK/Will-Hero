package code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Game {
    private Game game;
    private int score;
    private ArrayList<GameObject> gameObjects;
    private HighScore highScore;                        //an object of type HighScore to help in serialization
    private FileOutputStream fileOut;                   //the file writer to write to the file that we will serialise to
    private ObjectOutputStream out;                     //the object writer that will write to the file using the file writer
    private FileInputStream fileIn;                     //the file reader to read from the file that we will serialise to
    private ObjectInputStream in;                       //the object reader that will read from the file using the file reader
    private int lastSavedGameIndex;
    private ArrayList<Game> savedGames;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private AnchorPane pane;
    private FXMLLoader loader;

    public Game(Stage stage){
        game = this;
        gameObjects = new ArrayList<>();
        lastSavedGameIndex = 0;
        this.stage = stage;
    }


    public void startGame() throws IOException {
        serializeHighScore(69);
        stage.setTitle("Will Hero");
        Image icon = new Image("/assets/logo.png");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Start.fxml")));
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/assets/StyleSheet.css").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public void serializeHighScore(int currentScore){
        HighScore score = new HighScore();
        score.setHighScore(currentScore);
        try {
            FileOutputStream file = new FileOutputStream("serial/SerializedHighScore.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(score);
            out.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }


}