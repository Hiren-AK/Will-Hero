package code;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class bombTNT extends GameObjects{
    private Rectangle gameObject;

    public bombTNT(Rectangle _gameObject){
        super(_gameObject);
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/TNT.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
    }
}
