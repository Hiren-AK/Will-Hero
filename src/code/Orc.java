package code;

public class Orc extends GameObject{
    private int numHits;
    private  double sideHitDistance;

    public Orc(){
        super("Orc");
        numHits = 1;
        sideHitDistance = 2;
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
