package code;

public class Coin extends GameObject{
    private int value;
    public Coin(double _x, double _y){
        super("coin", _x, _y);
        value = 1;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

//    @Override
//    public int ifCollides(Hero hero){
//
//    }
    public void incrementOnCollisiion(){

    }

}