package code;

public class Coin extends GameObject{
    private int value;
    public Coin(){
        super("coin");
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