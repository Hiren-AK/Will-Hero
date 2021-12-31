package code;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private Coordinates coordinates;
    private String objectType;
    private int opacity;
    private Rectangle rectangle;

    public GameObject(String type){
        this.objectType = type;
    }

    public abstract boolean onCollide(GameObject collideObject);

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double _x, double _y) {
        this.coordinates.setX(_x);
        this.coordinates.setY(_y);
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public String getObjectType(){
        return this.getObjectType();
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
