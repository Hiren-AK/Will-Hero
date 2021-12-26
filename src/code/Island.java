package code;

public class Island extends GameObject{
    private double width;
    private double height;
    private double levitation;

    public Island(double _width, double _height, double _levitation){
        width = _width;
        height = _height;
        levitation = _levitation;
    }

//    @Override
//    public int ifCollides(Hero hero){
//
//    }

    public void collideObjects(GameObject obj){

    }
}