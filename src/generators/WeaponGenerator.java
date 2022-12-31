package generators;

import data.Weapon;
import main.MainApp;

import java.util.Random;

public class WeaponGenerator {

    public static Weapon generateWeapon(){
        int weaponDamage = 0;
        int weaponCriticalChance = 0;
        String weaponName = "Default Weapon";

        int playerLevel = MainApp.getPlayer().getLevel();
        int weaponPower = (playerLevel*6+8); //weaponPower is always even number*
        int playerCriticalChance = MainApp.getPlayer().getCriticalChance(); //can't be more than 75(%)

        Random rng = new Random();

        switch (rng.nextInt(0, 3)) {
            case 0 -> {
                weaponName = "Axe";
                weaponDamage = (weaponPower - 1);
                weaponCriticalChance = 1;

                if (playerCriticalChance + weaponCriticalChance > 75) {          //Player Critical Chance Corrector
                    int tmp = playerCriticalChance + weaponCriticalChance - 75;
                    weaponCriticalChance -= tmp;
                    weaponDamage += tmp;
                }

            }
            case 1 -> {
                weaponName = "Hammer";
                weaponCriticalChance = (weaponPower - 1);
                weaponDamage = 1;

                if (playerCriticalChance + weaponCriticalChance > 75) {
                    int tmp = playerCriticalChance + weaponCriticalChance - 75;
                    weaponCriticalChance -= tmp;
                    weaponDamage += tmp;
                }

            }
            case 2 -> {
                weaponName = "Sword";
                weaponDamage = weaponPower / 2;
                weaponCriticalChance = weaponPower / 2;

                if (playerCriticalChance + weaponCriticalChance > 75) {
                    int tmp = playerCriticalChance + weaponCriticalChance - 75;
                    weaponCriticalChance -= tmp;
                    weaponDamage += tmp;
                }

            }
        }

        return new Weapon(weaponName,weaponDamage,weaponCriticalChance);
    }


}
