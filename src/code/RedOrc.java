package code;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class RedOrc extends GameObjects{
    private Rectangle gameObject;

    public RedOrc(Rectangle _gameObject, ArrayList<Rectangle> redOrcList){
        super(_gameObject);
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/RedOrc.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
        redOrcList.add(gameObject);
    }
}
