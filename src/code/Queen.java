package code;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Queen {
    private Rectangle gameObject;

    public Queen(Rectangle _gameObject){
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/Queen.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
    }
}
