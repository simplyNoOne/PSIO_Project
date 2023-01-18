package interfaces;

import data.*;
import main.MainApp;
public interface GeneratesEnemy
{
    Enemy generate();
    default public int  calculateTotalAverageWeaponCombatStats()
    {
        double damage = 0;
        double criticalChance = 0;
        Inventory inventory = MainApp.getPlayer().getInventory();
        for (Weapon weapon: inventory.getWeapons())
        {
            damage += weapon.getDamage();
            criticalChance +=weapon.getCriticalChance();
        }
        damage /= (inventory.getNumOfWeapons()+1);
        criticalChance /= (inventory.getNumOfWeapons()+1);
        return (int)(damage + criticalChance);
    }
}
