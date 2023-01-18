package generators;
import data.Enemy;
import interfaces.GeneratesEnemy;
import main.MainApp;
public class EnemyCreator {

    GeneratesEnemy generatorType;
    public Enemy createEnemy()

    {
        MainApp.getPlayer().setEnemiesApproached(MainApp.getPlayer().getEnemiesApproached() + 1);

        if (MainApp.getPlayer().getEnemiesApproached() % 4 == 0)
        {
            generatorType = new BossEnemyGenerator();
        }
        else
        {
            generatorType = new CommonEnemyGenerator();
        }

        return generatorType.generate();
    }
}
