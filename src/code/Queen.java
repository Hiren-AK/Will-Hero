package code;

import java.util.Arrays;

public class Queen extends GameObject{
    private Weapons[] weapons = new Weapons[2];
    private boolean w1 = false;
    private boolean w2 = false;

    public Queen(double x, double y) {
        super("queen", x, y);
    }

    @Override
    public boolean onCollide(GameObject collideObject) {
        return false;
    }

    public void collectWeapon(Weapons w){
        if(w.getWeaponType() == 1){
            if(w1){
                if(weapons[0].getWeaponType() == 1){
                    weapons[0].upgrade();
                }
                else{
                    weapons[1].upgrade();
                }
            }
            else if(w2){
                weapons[1] = w;
                w1 = true;
            }
            else{
                weapons[0] = w;
                w1 = true;
            }
        }
        else{
            if(w2){
                if(weapons[0].getWeaponType() == 2){
                    weapons[0].upgrade();
                }
                else{
                    weapons[1].upgrade();
                }
            }
            else if(w1){
                weapons[1] = w;
                w2 = true;
            }
            else{
                weapons[0] = w;
                w2 = true;
            }
        }
    }
}
