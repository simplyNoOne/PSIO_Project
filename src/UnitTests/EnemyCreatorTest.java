package UnitTests;

import data.Enemy;
import data.Inventory;
import data.Weapon;
import generators.EnemyCreator;
import generators.WeaponGenerator;
import main.MainApp;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class EnemyCreatorTest {
    Random randomGenerator = new Random();
    EnemyCreator testEnemyCreator = new EnemyCreator();
    boolean isExpected = true;
    @Test
    void createEnemy() {

        MainApp.spawnPlayer();

        MainApp.getPlayer().setArmor(randomGenerator.nextInt(1, 101));
        MainApp.getPlayer().setLevel(1);
        MainApp.getPlayer().setName("TestPlayer");
        MainApp.getPlayer().setMaxHealth(randomGenerator.nextInt(100, 200));
        MainApp.getPlayer().setHealth(MainApp.getPlayer().getMaxHealth());
        MainApp.getPlayer().setEnemiesApproached(randomGenerator.nextInt(10, 101));
        MainApp.getPlayer().setCriticalChance(randomGenerator.nextInt(10, 51));
        MainApp.getPlayer().setDodgeChance(randomGenerator.nextInt(10, 101));
        MainApp.getPlayer().setBaseDamage(randomGenerator.nextInt(10, 101));


        for (int weaponID = 0; weaponID < randomGenerator.nextInt(0, 4); weaponID++) {
            MainApp.getPlayer().getInventory().addToInventory(WeaponGenerator.generateWeapon());
        }

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


        int averageWeaponCombatStats = (int)(damage + criticalChance);

        int playerCombatStats = MainApp.getPlayer().getMaxHealth() + MainApp.getPlayer().getBaseDamage() + MainApp.getPlayer().getDodgeChance() +
                MainApp.getPlayer().getArmor() + MainApp.getPlayer().getCriticalChance() + averageWeaponCombatStats;
        Enemy testEnemy = testEnemyCreator.createEnemy();



        if (MainApp.getPlayer().getEnemiesApproached() % 4 == 0)
        {

            if (!testEnemy.getIsBoss())
            {
                isExpected = false;
                System.out.println("Boss isBoss: " + testEnemy.getIsBoss());
            }

            if (testEnemy.getName().equals("DefaultEnemy"))
            {
                isExpected = false;
                System.out.println("Boss Name: " + testEnemy.getName());
            }
            if (!Objects.equals(testEnemy.getAbilityName(), "DefaultAbility"))
            {
                isExpected = false;
                System.out.println("Boss Ability Name: " + testEnemy.getAbilityName());

            }
            if (testEnemy.getHealth()<50 || testEnemy.getHealth()>0.85*playerCombatStats+1)
            {
                isExpected = false;
                System.out.println("Boss Health: " + testEnemy.getHealth() +"\n" + "Expected: (50,"+ ((int)(0.85*playerCombatStats+1))+ ")");
            }
            if(testEnemy.getBaseDamage()<30 || testEnemy.getBaseDamage()>0.85*playerCombatStats)
            {
                isExpected = false;
                System.out.println("Boss Damage: " + testEnemy.getBaseDamage()+"\n" + "Expected: (30,"+ ((int)(0.85*playerCombatStats+1))+ ")");

            }
            if(testEnemy.getCriticalChance()<10 || testEnemy.getCriticalChance()>75)
            {

                isExpected = false;
                System.out.println("Boss Critical Chance: " + testEnemy.getCriticalChance()+"\n" + "Expected: (10,75)");
            }

            if(testEnemy.getDodgeChance()<10 || testEnemy.getDodgeChance()>75)
            {
                isExpected = false;
                System.out.println("Boss Dodge Chance: "+testEnemy.getDodgeChance()+"\n" + "Expected: (10,75)");
            }

            if(testEnemy.getArmor()<10 || testEnemy.getArmor()>75) {


                isExpected = false;
                System.out.println("Boss Armor: " + testEnemy.getArmor()+"\n" + "Expected: (10,75)");
            }


        }
        else
        {

            if (testEnemy.getIsBoss())
            {
                isExpected = false;
                System.out.println("Common isBoss: " + testEnemy.getIsBoss());

            }
            if (testEnemy.getName().equals("DefaultEnemy"))
            {
                isExpected = false;
                System.out.println("Common Name: " + testEnemy.getName());
            }
            if (!Objects.equals(testEnemy.getAbilityName(), "DefaultAbility"))
            {
                isExpected = false;
                System.out.println("Common Ability Name: " + testEnemy.getAbilityName());

            }
            if (testEnemy.getHealth()<25 || testEnemy.getHealth()>0.55*playerCombatStats+1)
            {
                isExpected = false;
                System.out.println("Common Health: " + testEnemy.getHealth() +"\n" + "Expected: (25,"+ ((int)(0.55*playerCombatStats+1))+ ")");
            }
            if(testEnemy.getBaseDamage()<10 || testEnemy.getBaseDamage()>0.55*playerCombatStats)
            {
                isExpected = false;
                System.out.println("Common Damage: " + testEnemy.getBaseDamage()+"\n" + "Expected: (10,"+ ((int)(0.55*playerCombatStats+1))+ ")");

            }
            if(testEnemy.getCriticalChance()<5 || testEnemy.getCriticalChance()>75)
            {

                isExpected = false;
                System.out.println("Common Critical Chance: " + testEnemy.getCriticalChance()+"\n" + "Expected: (5,75)");
            }

            if(testEnemy.getDodgeChance()<5 || testEnemy.getDodgeChance()>75)
            {
                isExpected = false;
                System.out.println("Common Dodge Chance: "+testEnemy.getDodgeChance()+"\n" + "Expected: (5,75)");
            }

            if(testEnemy.getArmor()<5 || testEnemy.getArmor()>75)
            {

                isExpected = false;
                System.out.println("Common Armor: " + testEnemy.getArmor()+"\n" + "Expected: (5,75)");
            }


        }
        assertTrue(isExpected);
    }
}