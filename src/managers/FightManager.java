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

    private static String message; // fight round info displayed in GUI, like "Player hits enemy..."
    private static Character attacker; // abstract handle, can change between Player and Enemy. Convenient to use as a class field
    private static Character defender; // the other character
    private static Boolean playerWon = null; // Fight result. Wrapping as a non-primitive boolean gives possibility to set result as null when it's unknown

    private static final int DAMAGE_RANDOMIZATION_PERCENT = 10; // Damage slightly randomized e.g. base hit 100 will really be a random from 90 ... 110
    private static final int CRITICAL_MULTIPLIER = 2; // 2x (double) damage in case of a critical hit
    private static final int PHASE_DELAY_SECS = 2; // time interval between next phases in round (setup attacker, damage). Needed to update GUI for the user

    private static boolean isRoundStarting = true;
    private static double timeElapsedSinceHit;


    public static void init() {
        message = "Opponents approach each other...";

        attacker = null;
        defender = null;
        playerWon = null;

        isRoundStarting = true;
        timeElapsedSinceHit = 0;

        ((FightPanel) GUIManager.getPanel("fight")).setMessage(message);
    }


    public static void attemptToFightRound(double deltaTime) {
        timeElapsedSinceHit += deltaTime;
        if (timeElapsedSinceHit > PHASE_DELAY_SECS) {
            timeElapsedSinceHit = 0;

            if (shouldFightContinue()) {
                fightRound();
            } else {
                finishFight();
            }
        }
    }

    public static boolean shouldFightContinue() {
        if (playerWon == null) {
            return true;
        }
        return false;
    }

    private static void fightRound() {
        if (isRoundStarting) {
            prepareRound();
        }
        else { // damage time
            enterCombat();
        }
    }

    private static void prepareRound() {
        if (isFightInitialized()) {
            switchAttacker();
        }
        else { // fight hasn't even started before (= 1st round!)
           chooseAttackerAndDefender();
        }

        isRoundStarting = false; // round is set up and damage ready to be dealt
        message = "Now " + attacker.getName() + " charges...";
        ((FightPanel) GUIManager.getPanel("fight")).setMessage(message);

        MainApp.getGameFrame().repaint();
    }


    private static boolean isFightInitialized() {
        if (attacker == null || defender == null)
            return false;
        return true;
    }

    // swap attacker and defender
    private static void switchAttacker() {
        Character temp = attacker;
        attacker = defender;
        defender = temp;
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

    private static void enterCombat() {
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
        isRoundStarting = true;
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
            if (defender.getHealth() < 0)
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
            if (((Player) attacker).getActiveWeapon() != null) {
                damage += ((Player) attacker).getActiveWeapon().getDamage();
            }

        } else if (attacker instanceof Enemy) {
            damage = attacker.getBaseDamage();
        }

        int damageBonusPercent = (new Random()).nextInt(-DAMAGE_RANDOMIZATION_PERCENT, DAMAGE_RANDOMIZATION_PERCENT + 1); // bound exclusive so +1
        damage = (int) Math.round((1.0 + damageBonusPercent / 100.0) * damage);

        if (isCriticalAttack()) {
            damage *= CRITICAL_MULTIPLIER;
            message = "Critical Attack!... "; // temp
        }

        return damage;
    }

    private static boolean isCriticalAttack() {
        // criticalChance: 0 ... 100 %

        int criticalChance = attacker.getCriticalChance();
        if (attacker instanceof Player) {
            if(((Player) attacker).getActiveWeapon() != null)
                criticalChance += ((Player) attacker).getActiveWeapon().getCriticalChance();
        }

        if (criticalChance > 100)
            criticalChance = 100;

        if (criticalChance == 0)
            return false;
        else if (criticalChance == 100)
            return true;

        int criticalGenerated = (new Random()).nextInt(100);
        return (criticalGenerated < criticalChance); // e.g. if 35 is in [0, 70) == set representing probability = 70 %
    }

    /** If someone's HP goes to zero, fight is over and dead character loses. **/
    private static void setFightResult() {
        // Normally, either player or enemy survives. However, in case of a e.g. bleeding effect, both characters could die
        // The check of player health is done first, so that if player dies, player lost result is guaranteed
        // (Killing your enemy while getting bled out is a loss)
        if (MainApp.getPlayer().getHealth() <= 0) {
            playerWon = false;
        }
        else if (MainApp.getEnemy().getHealth() <= 0) {
            playerWon = true;
        } else {
            playerWon = null;
        }
    }

    private static void finishFight(){
        if (playerWon) {
            if(! MainApp.getEnemy().isCanPuzzle())
                StateMachine.setNextStateVar(StateMachine.State.LEVELUP);
            else
                StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);

        } else
            StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);

        init(); // needed for cleanup, resets FightManager for next fights
        StateMachine.nextState();
    }
}
