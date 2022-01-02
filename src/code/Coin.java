package code;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Coin {
    private Rectangle gameObject;

    public Coin(Rectangle _gameObject, ArrayList<Rectangle> coinList){
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/Coin.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
        coinList.add(gameObject);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameObject);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(2500));
        translate.setByY(-8);
        translate.setAutoReverse(true);
        translate.play();
    }
}
