package generators;
import data.Enemy;
import main.ManagerHandler;
import static java.lang.Math.max;
import static java.lang.Math.min;
public class CommonEnemyGenerator extends EnemyGenerator
{
    @Override
    public Enemy generate()
    {
        enemyIsBoss = false;

        int playerCombatStats = player.getMaxHealth() + player.getBaseDamage() + player.getDodgeChance() +
                player.getArmor() + player.getCriticalChance() + calculateTotalAverageWeaponCombatStats();

        enemyCombatStats = (int) (rng.nextFloat(0.45f, 0.55f) * playerCombatStats);

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
                enemyTexture = ManagerHandler.getResourceManager().getTexture("enemy2");
            }
            case 1 -> {
                enemyName = "Purple Clown";
                enemyTexture = ManagerHandler.getResourceManager().getTexture("enemy1");
            }
        }
        return new Enemy(enemyName,enemyHealth,enemyArmor,enemyDamage,enemyDodgeChance, enemyIsBoss,
                enemyAbilityName, enemyCriticalChance, enemyTexture);
    }
}
