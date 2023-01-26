package UnitTests;

import data.Player;
import main.MainApp;
import managers.LevelUpManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelUpManagerTest {

    LevelUpManager levelUpManager;
    Player player;

    @BeforeEach
    void reset() {
        levelUpManager = new LevelUpManager();
        MainApp.spawnPlayer();
        player = MainApp.getPlayer();
    }

    @Test
    void generalLevelUp() {
        int oldLevel = player.getLevel();

        levelUpManager.generalLevelUp();

        assertEquals(oldLevel + 1, player.getLevel()); // level should increase by 1

        assertEquals(player.getMaxHealth(), player.getHealth()); // max health should be now the current health (regeneration)
    }

    @Test
    void physicalGrowth() {
        int oldMaxHealth = player.getMaxHealth();
        int oldBaseDamage = player.getBaseDamage();

        levelUpManager.physicalGrowth();

        assertEquals((int) (1.3 * oldMaxHealth), player.getMaxHealth());
        assertEquals(player.getMaxHealth(), player.getHealth());

        assertEquals((int) (1.5 * oldBaseDamage), player.getBaseDamage());
    }

    @Test
    void dexterityTraining() {
        int oldDodgeChance = player.getDodgeChance();
        int oldCriticalChance = player.getCriticalChance();

        levelUpManager.dexterityTraining();

        assertEquals((int) (1.3 * oldDodgeChance), player.getDodgeChance());
        assertEquals((int) (1.2 * oldCriticalChance), player.getCriticalChance());
    }

    @Test
    void armorEnhancement() {
        int oldArmor = player.getArmor();

        levelUpManager.armorEnhancement();

        assertEquals((int) (oldArmor * 2.0), player.getArmor());
    }
}