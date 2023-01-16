package generators;

import data.Enemy;
import interfaces.GeneratedEnemy;
import main.MainApp;


public class EnemyGenerator {

    GeneratedEnemy generatorType;
    public Enemy generateEnemy()
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
