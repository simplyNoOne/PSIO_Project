package generators;
import data.Inventory;
import data.Player;
import data.Texture;
import data.Weapon;
import interfaces.GeneratesEnemy;
import main.MainApp;
import main.ManagerHandler;
import java.util.Random;
public abstract class EnemyGenerator implements GeneratesEnemy
{
    protected Random rng = new Random();
    protected Player player = MainApp.getPlayer();
    protected String enemyName = "DefaultEnemy";
    protected int enemyHealth;
    protected int enemyDamage;
    protected int enemyArmor;
    protected int enemyCriticalChance;
    protected int enemyDodgeChance;
    protected int enemyCombatStats;
    protected boolean enemyIsBoss;
    protected Texture enemyTexture = ManagerHandler.getResourceManager().getTexture("enemy");
    protected String enemyAbilityName = "DefaultAbility";
    public static int  calculateTotalAverageWeaponCombatStats()
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
