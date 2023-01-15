package generators;

import data.Collectible;
import data.Weapon;
import main.MainApp;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class LootGenerator {

    final static double COLLECTIBLE_MULTIPLIER = 1.2;
    final static int ARMOR_GRANTED = 3;
    public static  void generateLoot(boolean isBoss)
    {
        //First we need to check what does he have in his inventory
        int numOfCollectibles = MainApp.getPlayer().getInventory().getCollectibles().size();
        ArrayList<Collectible> possibleDrop = new ArrayList<>();
        AtomicBoolean hasCollectible1A = new AtomicBoolean(false);
        AtomicBoolean hasCollectible2A = new AtomicBoolean(false);
        AtomicBoolean hasCollectible3A = new AtomicBoolean(false);
        AtomicBoolean hasCollectible4A = new AtomicBoolean(false);
        AtomicBoolean hasCollectible5A = new AtomicBoolean(false);

        boolean hasCollectible1 = true;
        boolean hasCollectible2 = true;
        boolean hasCollectible3 = true;
        boolean hasCollectible4 = true;
        boolean hasCollectible5 = true;


        MainApp.getPlayer().getInventory().getCollectibles().forEach(collectible -> {
            if (Objects.equals(collectible.getName(), "collectible1"))
            {
                hasCollectible1A.set(true);
            }
            else if (Objects.equals(collectible.getName(), "collectible2"))
            {
                hasCollectible2A.set(true);
            }
            else if (Objects.equals(collectible.getName(), "collectible3"))
            {
                hasCollectible3A.set(true);
            }
            else if (Objects.equals(collectible.getName(), "collectible4"))
            {
                hasCollectible4A.set(true);
            }
            else if (Objects.equals(collectible.getName(), "collectible5"))
            {
                hasCollectible5A.set(true);
            }
        });

        hasCollectible1 = hasCollectible1A.get();
        hasCollectible2 = hasCollectible2A.get();
        hasCollectible3 = hasCollectible3A.get();
        hasCollectible4 = hasCollectible4A.get();
        hasCollectible5 = hasCollectible5A.get();

        //Actual Generator here
        Random rng = new Random();
        int randomNumber;

        if(isBoss)
        {
            Weapon generatedWeapon = WeaponGenerator.generateWeapon();
            MainApp.getPlayer().getInventory().getWeapons().forEach(weapon -> {
                if(weapon.getName().equals(generatedWeapon.getName()))
                {
                    MainApp.getPlayer().getInventory().getWeapons().remove(weapon);
                }
            });
            MainApp.getPlayer().getInventory().getWeapons().add(generatedWeapon);
        }
        else
        {
            if (numOfCollectibles==5)
            {
                MainApp.getPlayer().setArmor(MainApp.getPlayer().getArmor()+ARMOR_GRANTED);
            }
            else
            {
                switch(rng.nextInt(0,2))
                {
                    case 0:
                    {
                        MainApp.getPlayer().setArmor(MainApp.getPlayer().getArmor()+ARMOR_GRANTED);
                    }
                    case 1:
                    {
                        if (!hasCollectible1)
                        {
                            possibleDrop.add(new Collectible("collectible1",COLLECTIBLE_MULTIPLIER));
                        }
                        else if (!hasCollectible2)
                        {
                            possibleDrop.add(new Collectible("collectible2",COLLECTIBLE_MULTIPLIER));
                        }
                        else if (!hasCollectible3)
                        {
                            possibleDrop.add(new Collectible("collectible3",COLLECTIBLE_MULTIPLIER));
                        }
                        else if (!hasCollectible4)
                        {
                            possibleDrop.add(new Collectible("collectible4",COLLECTIBLE_MULTIPLIER));
                        }
                        else if (!hasCollectible5)
                        {
                            possibleDrop.add(new Collectible("collectible5",COLLECTIBLE_MULTIPLIER));
                        }
                        randomNumber = rng.nextInt(0,possibleDrop.size());
                        MainApp.getPlayer().getInventory().addCollectible(possibleDrop.get(randomNumber));
                    }
                }
            }

        }
    }
}
