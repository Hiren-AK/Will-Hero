package code;

public class Weapons extends GameObject{
    private int level;
    private boolean throwable;

    public Weapons(int _level, boolean _throwable, double _x, double _y){
        super("weapon", _x, _y);
        level = _level;
        throwable = _throwable;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

    public void use(){

    }

    public void upgrade(){

    }
}
