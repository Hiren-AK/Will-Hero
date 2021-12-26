package code;

public class Orc extends GameObject{
    private int numHits;
    private  double sideHitDistance;
    private boolean armed;

    public Orc(double _x, double _y){
        super("Orc", _x, _y);
        numHits = 1;
        sideHitDistance = 2;
        armed = true;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

//    @Override
//    public int ifCollides(Hero hero){
//
//    }

    public  void jump(){

    }
}
