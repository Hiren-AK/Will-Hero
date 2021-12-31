package code;

import java.util.ArrayList;

public class Chest extends GameObject {
    private ArrayList<Coin> coins_list = new ArrayList<Coin>();
    private Weapons weapon;
    private int chestType;

    public Chest(ArrayList<Coin> _coins_list, Weapons _weapon, int _chestType) {
        super("chest");
        coins_list = _coins_list;
        weapon = _weapon;
        chestType = -_chestType;
    }

    public boolean onCollide(GameObject collideObject){
        //
        return true;
    }

//    @Override
//    public int ifCollides(Hero hero){
//
//    }

    public void collectChest(){

    }

    public void weaponUpgrade(){

    }
}
