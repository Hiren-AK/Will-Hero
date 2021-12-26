package code;

public class TNT extends GameObject{
    private int delay;
    private int explosionRadius;

    public TNT(int _delay, int _explosionRadius, double _x, double _y){
        super("tnt", _x, _y);
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
