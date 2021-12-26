package code;

import java.util.ArrayList;

public class Boss extends Orc{
    private double jumpHeight;

    public Boss(double _jumpHeight, double _x, double _y){
        super(_x, _y);
        jumpHeight = _jumpHeight;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

//    @Override
//    public void jump(){
//
//    }

    public void useWeapon(){

    }
}
