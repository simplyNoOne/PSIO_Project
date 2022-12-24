package data;

import data.Character;
import data.Inventory;

public class Player extends Character {
    private int currentHealth;
    private int Level;
    private Inventory inventory;

    public Player()
    {
        super("Player",100,5,10,5,5);
        this.currentHealth = this.getMaxHealth();
        this.Level = 1;
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHp) {
        this.currentHealth = currentHp;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }
}
