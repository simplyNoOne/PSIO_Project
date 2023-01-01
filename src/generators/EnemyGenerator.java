package generators;
import data.*;
import main.MainApp;
import java.util.Random;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class EnemyGenerator {

    public static Enemy generateEnemy()
    {
        Random rng = new Random();
        Player player = MainApp.getPlayer();

        String enemyName = "DefaultEnemy";
        int enemyHealth;
        int enemyDamage;
        int enemyArmor;
        int enemyCriticalChance;
        int enemyDodgeChance;
        int enemyCombatStats;
        boolean enemyCanPuzzle = true;
        String enemyAbilityName = "DefaultAbility";
        
        int playerCombatStats = player.getMaxHealth() + player.getBaseDamage() + player.getDodgeChance() +
                                   player.getArmor() + player.getCriticalChance() + calculateTotalAverageWeaponCombatStats();

        enemyCombatStats = (int)(rng.nextFloat(0.6f,0.80f)*playerCombatStats);

        enemyHealth = max(25, (int) (rng.nextFloat(0, 1) * enemyCombatStats));
        enemyCombatStats -= enemyHealth;

        enemyDamage = max(10, (int) (rng.nextFloat(0, 1) * enemyCombatStats));
        enemyCombatStats -= enemyDamage;

        enemyCriticalChance = min(75, max(5, (int) (rng.nextFloat(0, 1) * enemyCombatStats)));
        enemyCombatStats -= enemyCriticalChance;

        enemyDodgeChance = min(75, max(5, (int) (rng.nextFloat(0, 1) * enemyCombatStats)));
        enemyCombatStats -= enemyDodgeChance;

        enemyArmor = min(75, max(5, (int) (rng.nextFloat(0, 1) * enemyCombatStats)));
        enemyCombatStats -= enemyArmor;

        enemyHealth += max(1, enemyCombatStats);


        return new Enemy(enemyName,enemyHealth,enemyArmor,enemyDamage,enemyDodgeChance, enemyCanPuzzle,
                         enemyAbilityName, enemyCriticalChance);
    }
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
        damage /= inventory.getNumOfWeapons();
        criticalChance /= inventory.getNumOfWeapons();
        return (int)(damage + criticalChance);
    }
}
