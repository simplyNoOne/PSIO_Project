package managers;

import data.Character;
import data.Enemy;
import data.Player;
import gui.panels.FightPanel;
import gui.panels.StatsPanel;
import main.MainApp;
import main.StateMachine;
import java.util.Random;

public class FightManager {

    private static boolean isBeginning;

    private static double elapsedTime;
    static int rounds;
    private static String message;
    private static Character attacker; // abstract handle, can change between Player and Enemy. Convenient to use as a class field
    private static Character defender; // the other character
    private static Boolean playerWon = null; // Fight result. Wrapping as a non-primitive boolean gives possibility to set result as null when it's unknown
    private static final int DAMAGE_RANDOMIZATION_PERCENT = 10; // Damage slightly randomized e.g. base hit 100 will really be a random from 90 ... 110





    public static void init() {
        message = "Opponents approach each other...";
        rounds = 0;
        ((FightPanel) GUIManager.getPanel("fight")).setMessage(message);
    }


    public static void fightRound(double dT) {

        elapsedTime += dT;
        if(elapsedTime > 2) {
            elapsedTime = 0;
            if(playerWon != null)
                fightFinished();
            else {
                isBeginning = !isBeginning;
                if (isBeginning) {
                    rounds++;
                    if (rounds == 1) {
                        resetFightResult();
                        // Setup who attacks first and the other one defends. Based on flipping a coin (50/50 chance).
                        chooseAttackerAndDefender();
                    } else
                        switchAttacker();

                    System.out.println("Fight!");

                    message = "Now " + attacker.getName() + " charges...";
                    ((FightPanel) GUIManager.getPanel("fight")).setMessage(message);

                    MainApp.getGameFrame().repaint();

                } else {
                    message = "";
                    if (hasDefenderDodged()) {
                        message = defender.getName() + " has dodged the attack!";
                    } else {
                        dealDamage();
                    }
                    if (defender instanceof Player)
                        ((StatsPanel) GUIManager.getPanel("playerStats")).updateStats();
                    else
                        ((StatsPanel) GUIManager.getPanel("enemyStats")).updateStats();

                    ((FightPanel) GUIManager.getPanel("fight")).setMessage(message);
                    MainApp.getGameFrame().repaint();

                    setFightResult();
                }
            }
        }
    }

    private static void fightFinished(){
        if (playerWon) {
            if(!((Enemy)defender).isCanPuzzle())
                StateMachine.setNextStateVar(StateMachine.State.LEVELUP);
            else
                StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);

        } else
            StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);

        fightCleanup();
        StateMachine.nextState();
    }


    private static void resetFightResult() {
        playerWon = null;
    }

    private static void chooseAttackerAndDefender() {
        // Flips a coin to decide who attacks
        boolean playerAttacks = (new Random()).nextBoolean();

        if (playerAttacks) {
            attacker = MainApp.getPlayer(); // set the player as an attacker
            defender = MainApp.getEnemy();
        } else { // enemy attacks
            attacker = MainApp.getEnemy(); // set the enemy as an attacker
            defender = MainApp.getPlayer();
        }
    }

    /**
     * TODO best to create new small class e.g. RandomEventManager with method: public boolean hasProbabilisticEventOccurred(int eventChance) -> used for critical hit, dodge etc.
     **/
    private static boolean hasDefenderDodged() {
        // dodgeChance: 0 ... 100 %

        if (defender.getDodgeChance() == 0)
            return false;
        else if (defender.getDodgeChance() == 100)
            return true;

        int dodgeGenerated = (new Random()).nextInt(100);
        return (dodgeGenerated < defender.getDodgeChance()); // e.g. if 35 is in [0, 70) == set representing probability = 70 %

    }

    private static void dealDamage() {
        int damage = calculateDamage();
        damage -= defender.getArmor();

        if (damage > 0) {
            defender.setHealth(defender.getHealth() - damage);
            if(defender.getHealth() < 0)
                defender.setHealth(0);
            message += String.format("Boom! %s hits %s with %d damage!\n", attacker.getName(),  defender.getName(), damage);
        }
        else {
            message += String.format("%s 's armor has blocked the attack!\n", defender.getName());
        }
    }

    private static int calculateDamage() {
        int damage = 0;

        if (attacker instanceof Player) {
            damage += attacker.getBaseDamage();

            // FIXME TODO how to get the weapon selected for the fight? Any getActiveWeapon() there?
            if (((Player) attacker).getActiveWeapon() != null) {
                damage += ((Player) attacker).getActiveWeapon().getDamage();
            }
        } else if (attacker instanceof Enemy) {
            damage = attacker.getBaseDamage();
        } else throw new RuntimeException("Unhandled Character child class: " + attacker.getClass());

        int damageBonusPercent = (new Random()).nextInt(-DAMAGE_RANDOMIZATION_PERCENT, DAMAGE_RANDOMIZATION_PERCENT + 1); // bound exclusive so +1
        damage = (int) Math.round((1.0 + damageBonusPercent / 100.0) * damage);

        if (isCriticalAttack()) {
            damage *= 2; // critical multiplier
            message = "Critical Attack!... "; // temp
        }

        return damage;
    }

    // TODO probably should also include critical chance from weapons
    private static boolean isCriticalAttack() {
        // criticalChance: 0 ... 100 %

        int criticalChance = attacker.getCriticalChance();
        if(attacker instanceof  Player) {
            if(((Player) attacker).getActiveWeapon() != null)
                criticalChance += ((Player) attacker).getActiveWeapon().getCriticalChance();
        }

        if(criticalChance > 100)
            criticalChance = 100;

        if (criticalChance == 0)
            return false;
        else if (criticalChance == 100)
            return true;

        int criticalGenerated = (new Random()).nextInt(100);
       return (criticalGenerated < criticalChance); // e.g. if 35 is in [0, 70) == set representing probability = 70 %

    }

    // swap attacker and defender
    private static void switchAttacker() {
        Character temp = attacker;
        attacker = defender;
        defender = temp;
    }

    private static void setFightResult() {
        if (MainApp.getEnemy().getHealth() <= 0) {
            playerWon = true;
        } else if (MainApp.getPlayer().getHealth() <= 0) {
            playerWon = false;
        } else {
            playerWon = null;
        }
    }

    private static void fightCleanup() {
        attacker = null;
        defender = null;
        rounds = 0;
        isBeginning = false;
        message = "Opponents approach each other...";
        ((FightPanel) GUIManager.getPanel("fight")).setMessage(message);
    }
}
