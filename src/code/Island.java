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
    private Image islandImage;

    public Island(String name, String id){
        islandImage = new Image(this.getClass().getResource(name).toString());
    }

    public Image getIslandImage(){
        return islandImage;
    }
}