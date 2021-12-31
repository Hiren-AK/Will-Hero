package code;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Island{
    private double width;
    private double height;
    private Rectangle islandRectangle;
    private Image islandImage;

    public Island(String name){
        islandImage = new Image(this.getClass().getResource(name).toString());
        islandRectangle = new Rectangle();
        islandRectangle.setFill(new ImagePattern(islandImage));
        islandRectangle.setStrokeWidth(0);
    }

    public Rectangle getIslandRectangle(){
        return islandRectangle;
    }
}