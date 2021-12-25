package code;

public class Coordinates {
    private double x;
    private double y;

    public Coordinates(double _x, double _y){
        x = _x;
        y = _y;
    }

    public void setX(int _x){
        x = _x;
    }

    public void setY(int _y){
        y = _y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
