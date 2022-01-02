package code;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Treasure {
    private Rectangle gameObject;

    public Treasure(Rectangle _gameObject, ArrayList<Rectangle> treasureList){
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/ClosedTreasure.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
        gameObject.setFill(new ImagePattern(gameObjectImage));
        gameObject.setStrokeWidth(0);
        treasureList.add(gameObject);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameObject);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(2500));
        translate.setByY(-4);
        translate.setAutoReverse(true);
        translate.play();
    }
}
