package code;

import javafx.scene.shape.Rectangle;

public class Orc extends GameObjects{
    private Rectangle gameObject;

    public Orc(Rectangle _gameObject){
        super(_gameObject);
        gameObject = _gameObject;
    }
}
