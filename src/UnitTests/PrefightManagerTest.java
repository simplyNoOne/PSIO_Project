package UnitTests;

import data.Collectible;
import data.Weapon;
import gui.panels.PrefightPanel;
import main.MainApp;
import main.ManagerHandler;
import managers.PrefightManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrefightManagerTest {

    PrefightManager testManager = new PrefightManager();
    ArrayList<String> posCollectibles = new ArrayList<>();
    ArrayList<String> posWeapons = new ArrayList<>();

    Random rand = new Random();
    int numCols;
    int numWeps;


    void loadItems(){
        posCollectibles.clear();
        posWeapons.clear();

        posCollectibles.add("collectible1");
        posCollectibles.add("collectible2");
        posCollectibles.add("collectible3");
        posCollectibles.add("collectible4");
        posCollectibles.add("collectible5");

        posWeapons.add("axe");
        posWeapons.add("sword");
        posWeapons.add("dagger");
    }

    @BeforeAll
    void getPrep(){
        ManagerHandler.getResourceManager().loadResources();
        MainApp.spawnPlayer();
        ManagerHandler.getGUIManager().initAllPanels();
    }

    @BeforeEach
    void prep(){
        loadItems();
        MainApp.getPlayer().getInventory().clearInventory();
        numWeps = rand.nextInt(3)+1;
        for (int i = 0; i < numWeps; i++){
            int at = rand.nextInt(posWeapons.size());
            MainApp.getPlayer().getInventory().addToInventory(new Weapon(posWeapons.get(at), 0, 0));
            posWeapons.remove(at);
        }

        numCols = rand.nextInt(5)+1;
        for (int i = 0; i < numCols; i++){
            int at = rand.nextInt(posCollectibles.size());
            MainApp.getPlayer().getInventory().addToInventory(new Collectible(posCollectibles.get(at), 0));
            posCollectibles.remove(at);
        }
    }

    @Test
    void getActiveCollectibles() {
       ArrayList<String> collectibles = testManager.getActiveCollectibles();

        if(collectibles.size() != 5 - posCollectibles.size())
            assert false;

        for( String col : collectibles){
            if(posCollectibles.contains(col))
                assert(false);
        }
        assert (true);
    }


    @Test
    void getActiveWeapons() {
        ArrayList<String> weapons = testManager.getActiveWeapons();
        if(weapons.size() != 3 - posWeapons.size())
            assert false;

        for( String wep : weapons){
            if(posWeapons.contains(wep))
                assert(false);
        }
        assert (true);
    }
}