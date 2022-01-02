package code;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GreenOrc extends Orc{
    private Rectangle gameObject;

    public GreenOrc(Rectangle _gameObject, ArrayList<Rectangle> greenOrcList){
        super(_gameObject);
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/GreenOrc.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
        greenOrcList.add(gameObject);
    }
}
