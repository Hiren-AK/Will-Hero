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

    public Island island1;
    public Island island2;
    public Island island3;
    public Island island4;
    public Island island5;
    public Island island6;

    public Game(){
        game = this;
        score = 0;
        gameObjects = new ArrayList<>();
        savedGames = new ArrayList<>();
        lastSavedGameIndex = 0;

        //Islands
        island1 = new Island("/assets/Island1.png");
        island2 = new Island("/assets/Island3.png");
        island3 = new Island("/assets/Island4.png");
        island4 = new Island("/assets/Island2.png");
        island5 = new Island("/assets/Island6.png");
        island6 = new Island("/assets/Island5.png");
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int _score){
        this.score = _score;
    }

    public void addGameObject(GameObject obj){
        gameObjects.add(obj);
    }
}