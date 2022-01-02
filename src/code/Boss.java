package code;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Boss extends Orc{
    private Rectangle gameObject;

    public Boss(Rectangle _gameObject, ArrayList<Rectangle> bossOrcList){
        super(_gameObject);
        Image gameObjectImage = new Image(this.getClass().getResource("/assets/BossOrc.png").toString());
        _gameObject.setFill(new ImagePattern(gameObjectImage));
        _gameObject.setStrokeWidth(0);
        gameObject = _gameObject;
        bossOrcList.add(gameObject);
    }
}
