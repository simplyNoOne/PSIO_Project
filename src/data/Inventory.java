package data;

import java.util.*;

public class Inventory {
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    // private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();          For Wlad!!!!!!

    public int getNumOfWeapons(){
        return this.weapons.size();
    }

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public Weapon getWeaponAt(int x){
        return this.weapons.get(x);
    }

    public void deleteWeaponAt(int x){
        weapons.remove(weapons.get(x));
    }


    

    
    
}
