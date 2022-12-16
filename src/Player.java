import java.util.ArrayList;

public class Player extends Character{
    private double damage;
    Inventory inventory = new Inventory();

    public double getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = inventory.getWeaponAt(damage).getDamage();
    }

    






    
}
