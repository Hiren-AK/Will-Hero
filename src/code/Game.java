package code;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class Game {
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

    public Game(Stage stage){
        game = this;
        gameObjects = new ArrayList<>();
        savedGames = new ArrayList<>();
        lastSavedGameIndex = 0;
        this.stage = stage;
        deserializeGames();
        serializeCurrentGame();
    }

    public void startGame() throws IOException {
        serializeHighScore(69);
        stage.setTitle("Will Hero");
        Image icon = new Image("/assets/logo.png");
        loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        root = loader.load();
        scene = new Scene(root);
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

    public void deserializeGames(){
        Game tempGame = null;
        try {
            fileIn = new FileInputStream("serial/SerializedGame1.txt");
            in = new ObjectInputStream(fileIn);
            tempGame = (Game) in.readObject();
            savedGames.add(tempGame);
            in.close();
            fileIn.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        }
        catch (Exception ex) {
            System.out.println("Exception" + " is caught");
        }

        try {
            fileIn = new FileInputStream("serial/SerializedGame2.txt");
            in = new ObjectInputStream(fileIn);
            tempGame = (Game) in.readObject();
            savedGames.add(tempGame);
            in.close();
            fileIn.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        }
        catch (Exception ex) {
            System.out.println("Exception" + " is caught");
        }

        try {
            fileIn = new FileInputStream("serial/SerializedGame2.txt");
            in = new ObjectInputStream(fileIn);
            tempGame = (Game) in.readObject();
            savedGames.add(tempGame);
            in.close();
            fileIn.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        }
        catch (Exception ex) {
            System.out.println("Exception" + " is caught");
        }
    }

    public void serializeCurrentGame(){
        try {
            fileOut = new FileOutputStream("serial/SerializedCurrentGame.txt");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}