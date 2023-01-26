package UnitTests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

import generators.EnemyCreator;
import generators.WeaponGenerator;
import main.MainApp;
import data.*;
public class CollecibllesTest {
    boolean isExpected = true;
    
    @BeforeAll
    public static void init(){
        MainApp.spawnPlayer();
        MainApp.getPlayer().setHealth(MainApp.getPlayer().getHealth()/2);
        MainApp.getPlayer().getInventory().addToInventory((new Collectible("collectible1" , 1.2)));
        MainApp.getPlayer().getInventory().addToInventory((new Collectible("collectible2" , 1.3)));
    }


    @Test
    void CheckIncreaseHealthCollectible(){
        int OldPlayerHealth = MainApp.getPlayer().getHealth();
        MainApp.getPlayer().increase_health(MainApp.getPlayer().getInventory().getCollectibleByName("collectible1").getValue());
        isExpected = (OldPlayerHealth*MainApp.getPlayer().getInventory().getCollectibleByName("collectible1").getValue() == MainApp.getPlayer().getHealth());


        assertTrue(isExpected);
    }

    @Test
    void CheckIncreaseArmour(){
        int OldPlayerArmour = MainApp.getPlayer().getArmor();
        MainApp.getPlayer().increase_armour(MainApp.getPlayer().getInventory().getCollectibleByName("collectible2").getValue());
        isExpected = (OldPlayerArmour*MainApp.getPlayer().getInventory().getCollectibleByName("collectible2").getValue() == MainApp.getPlayer().getArmor());
        
        assertTrue(isExpected);
    }

    
    
}
