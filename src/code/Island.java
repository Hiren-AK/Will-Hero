package code;

public class Island extends GameObject{
    private double width;
    private double height;
    private double levitation;

    public Island(double _width, double _height, double _levitation, double _x, double _y){
        super("island", _x, _y);
        width = _width;
        height = _height;
        levitation = _levitation;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

//    @Override
//    public int ifCollides(Hero hero){
//
//    }

    public void collideObjects(GameObject obj){

    }
}