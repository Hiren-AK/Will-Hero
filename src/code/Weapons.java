package code;

public class Weapons extends GameObject{
    private int weaponType;
    private int level;
    private boolean throwable;

    public Weapons(int _weaponType, boolean _throwable){
        super("weapon");
        level = 0;
        weaponType = _weaponType;
        throwable = _throwable;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

    public int getWeaponType(){
        return weaponType;
    }

    public void use(){

    }

    public void upgrade(){

    }
}