package generators;
import data.Enemy;
import main.ManagerHandler;
import static java.lang.Math.max;
import static java.lang.Math.min;
public class BossEnemyGenerator extends EnemyGenerator
{
    @Override
    public Enemy generate()
    {
        enemyName = "Demon King";
        enemyIsBoss = true;
        enemyTexture = ManagerHandler.getResourceManager().getTexture("boss");

        int playerCombatStats = player.getMaxHealth() + player.getBaseDamage() + player.getDodgeChance() +
                player.getArmor() + player.getCriticalChance() + calculateTotalAverageWeaponCombatStats();

        enemyCombatStats = (int)(rng.nextFloat(0.9f,1f)*playerCombatStats);

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
