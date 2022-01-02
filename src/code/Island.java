package code;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Island {
    private Rectangle gameObject;

    public Island(Rectangle _gameObject, String name, ArrayList<Rectangle> islandList){
        Image gameObjectImage = new Image(this.getClass().getResource(name).toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
        islandList.add(gameObject);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameObject);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(2500));
        translate.setByY(-3);
        translate.setAutoReverse(true);
        translate.play();
    }
}
