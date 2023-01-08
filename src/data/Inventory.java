package data;

import java.util.*;

public class Inventory {
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();


    public int getNumOfWeapons(){
        return this.weapons.size();
    }

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addCollectible(Collectible collectible){
        collectibles.add(collectible);
    }


    public ArrayList<Collectible> getCollectibles() { return collectibles; }

    public Weapon getWeaponAt(int x){
        return this.weapons.get(x);
    }

    public void deleteWeaponAt(int x){
        weapons.remove(weapons.get(x));
    }

    public void deleteCollectible(Collectible col){
        
            collectibles.remove(col);
        
    }


    

    
    
}
