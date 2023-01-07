package generators;
import data.*;
import main.MainApp;
import main.ManagerHandler;
import managers.ResourceManager;

import java.util.Random;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class BossGenerator {

    public static Enemy generateBoss()
    {
        Random rng = new Random();
        Player player = MainApp.getPlayer();

        String bossName = "Demon King";
        int bossHealth;
        int bossDamage;
        int bossArmor;
        int bossCriticalChance;
        int bossDodgeChance;
        int bossCombatStats;
        boolean bossIsBoss = true;
        Texture bossTexture = ManagerHandler.getResourceManager().getTexture("boss");
        String bossAbilityName = "DefaultAbility";

        int playerCombatStats = player.getMaxHealth() + player.getBaseDamage() + player.getDodgeChance() +
                player.getArmor() + player.getCriticalChance() + calculateTotalAverageWeaponCombatStats();

        bossCombatStats = (int)(rng.nextFloat(1f,1.1f)*playerCombatStats);

        bossHealth = max(50, (int) (rng.nextFloat(0, 1) * bossCombatStats));
        bossCombatStats -= bossHealth;

        bossDamage = max(30, (int) (rng.nextFloat(0, 1) * bossCombatStats));
        bossCombatStats -= bossDamage;

        bossCriticalChance = min(75, max(10, (int) (rng.nextFloat(0, 1) * bossCombatStats)));
        bossCombatStats -= bossCriticalChance;

        bossDodgeChance = min(75, max(10, (int) (rng.nextFloat(0, 1) * bossCombatStats)));
        bossCombatStats -= bossDodgeChance;

        bossArmor = min(75, max(10, (int) (rng.nextFloat(0, 1) * bossCombatStats)));
        bossCombatStats -= bossArmor;

        bossHealth += max(1, bossCombatStats);


        return new Enemy(bossName,bossHealth,bossArmor,bossDamage,bossDodgeChance, bossIsBoss,
                         bossAbilityName, bossCriticalChance, bossTexture);
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