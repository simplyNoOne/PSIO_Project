package data;

import java.util.*;

public class Inventory {
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();


    public int getNumOfWeapons(){
        return this.weapons.size();
    }


    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }


    public ArrayList<Collectible> getCollectibles() { return collectibles; }

    public <T> void  addToInventory(T item){
        if(item instanceof Weapon){
            weapons.add((Weapon)item);
        }
        else if( item instanceof Collectible){
            collectibles.add((Collectible) item);
        }
    }


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
