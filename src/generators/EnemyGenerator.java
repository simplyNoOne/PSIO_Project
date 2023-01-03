package generators;
import data.*;
import main.MainApp;
import managers.ResourceManager;

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
        Texture enemyTexture = ResourceManager.getTexture("enemy");
        boolean enemyIsBoss = false;
        String enemyAbilityName = "DefaultAbility";

        int playerCombatStats = player.getMaxHealth() + player.getBaseDamage() + player.getDodgeChance() +
                                   player.getArmor() + player.getCriticalChance() + calculateTotalAverageWeaponCombatStats();

        enemyCombatStats = (int)(rng.nextFloat(0.35f,0.45f)*playerCombatStats);

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

        switch (rng.nextInt(0, 2)) {
            case 0 -> {
                enemyName = "Blue Demon";
                enemyTexture = ResourceManager.getTexture("enemy2");
            }
            case 1 -> {
                enemyName = "Purple Clown";
                enemyTexture = ResourceManager.getTexture("enemy1");
            }
        }

        return new Enemy(enemyName,enemyHealth,enemyArmor,enemyDamage,enemyDodgeChance, enemyIsBoss,
                         enemyAbilityName, enemyCriticalChance, enemyTexture);
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
