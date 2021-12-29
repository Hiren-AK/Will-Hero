package code;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class Game implements Serializable{
    private Game game;
    private int score;
    private ArrayList<GameObject> gameObjects;
    private HighScore highScore;                        //an object of type HighScore to help in serialization
    private FileOutputStream fileOut;                      //the file writer to write to the file that we will serialise to
    private ObjectOutputStream out;                     //the object writer that will write to the file using the file writer
    private FileInputStream fileIn;                       //the file reader to read from the file that we will serialise to
    private ObjectInputStream in;                       //the object reader that will read from the file using the file reader
    private int lastSavedGameIndex;
    private ArrayList<Game> savedGames;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private AnchorPane pane;
    private FXMLLoader loader;

    public Game(){
        game = this;
        score = 0;
        gameObjects = new ArrayList<>();
        savedGames = new ArrayList<>();
        lastSavedGameIndex = 0;
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

    public int getScore(){
        return this.score;
    }

    public void setScore(int _score){
        this.score = _score;
    }
}