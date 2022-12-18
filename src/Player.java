import java.util.ArrayList;

public class Player extends Character{
    private int level;

    private Inventory inventory = new Inventory();

    public Inventory getInventory() {
        return inventory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    
}
