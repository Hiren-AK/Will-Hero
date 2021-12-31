package code;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;

public class Island{
    private double width;
    private double height;
    private Rectangle islandRectangle;
    private Image islandImage;
    private String some;

    public Island(String name, String id){
        islandImage = new Image(this.getClass().getResource(name).toString());
        islandRectangle = new Rectangle();
        islandRectangle.setFill(new ImagePattern(islandImage));
        islandRectangle.setWidth(227);
        islandRectangle.setHeight(63);
        islandRectangle.setStroke(Color.BLACK);
        islandRectangle.setStrokeWidth(0);
        islandRectangle.setId(id);
        some = name;
    }

    public Rectangle getIslandRectangle(){
        return islandRectangle;
    }

    public String getSome() {
        return some;
    }
}