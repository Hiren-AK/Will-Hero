package code;

public class TNT extends GameObject{
    private int delay;
    private int explosionRadius;

    public TNT(int _delay, int _explosionRadius){
        super("tnt");
        delay = _delay;
        explosionRadius = _explosionRadius;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

//    @Override
//    public int ifCollides(Hero hero){
//
//    }

    public void explode(){

    }
}
