package generators;
import data.Enemy;
import data.Player;
import data.Texture;
import interfaces.GeneratesEnemy;
import main.MainApp;
import main.ManagerHandler;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;
public class BossEnemyGenerator implements GeneratesEnemy
{
    @Override
    public Enemy generate()
    {
        Random rng = new Random();
        Player player = MainApp.getPlayer();
        int enemyHealth;
        int enemyDamage;
        int enemyArmor;
        int enemyCriticalChance;
        int enemyDodgeChance;
        int enemyCombatStats;
        boolean enemyIsBoss;
        String enemyAbilityName = "DefaultAbility";
        String enemyName = "Demon King";
        enemyIsBoss = true;
        Texture enemyTexture = ManagerHandler.getResourceManager().getTexture("boss");

        int playerCombatStats = player.getMaxHealth() + player.getBaseDamage() + player.getDodgeChance() +
                player.getArmor() + player.getCriticalChance() + calculateTotalAverageWeaponCombatStats();

        enemyCombatStats = (int)(rng.nextFloat(0.75f,0.85f)*playerCombatStats);

        enemyHealth = max(50, (int) (rng.nextFloat(0, 0.8f) * enemyCombatStats));
        enemyCombatStats -= enemyHealth;

        enemyDamage = max(30, (int) (rng.nextFloat(0, 1) * enemyCombatStats));
        enemyCombatStats -= enemyDamage;

        enemyCriticalChance = min(75, max(10, (int) (rng.nextFloat(0, 1) * enemyCombatStats)));
        enemyCombatStats -= enemyCriticalChance;

        enemyDodgeChance = min(75, max(10, (int) (rng.nextFloat(0, 1) * enemyCombatStats)));
        enemyCombatStats -= enemyDodgeChance;

        enemyArmor = min(20, max(10, (int) (rng.nextFloat(0, 1) * enemyCombatStats)));
        enemyCombatStats -= enemyArmor;

        enemyHealth += max(1, enemyCombatStats);

        return new Enemy(enemyName,enemyHealth,enemyArmor,enemyDamage,enemyDodgeChance, enemyIsBoss,
                enemyAbilityName, enemyCriticalChance, enemyTexture);
    }
}
