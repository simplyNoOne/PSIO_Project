package managers;

import data.Character;
import data.Enemy;
import data.Player;
import gui.panels.FightPanel;
import gui.panels.StatsPanel;
import interfaces.Interactible;
import main.MainApp;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FightManager {
    private static String message;
    private static Character attacker; // abstract handle, can change between Player and Enemy. Convenient to use as a class field
    private static Character defender; // the other character
    private static Boolean playerWon = null; // Fight result. Wrapping as a non-primitive boolean gives possibility to set result as null when it's unknown
    private static final int DAMAGE_RANDOMIZATION_PERCENT = 10; // Damage slightly randomized e.g. base hit 100 will really be a random from 90 ... 110

    public static class WonFightButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
            StateMachine.nextState();
        }
    }

    public static class LostFightButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);
            StateMachine.nextState();
        }
    }

    public static class WonAndLevelupFightButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            StateMachine.setNextStateVar(StateMachine.State.LEVELUP);
            StateMachine.nextState();
        }
    }

    private static final WonFightButtonListener wonFightButtonListener = new WonFightButtonListener();
    private static final LostFightButtonListener lostFightButtonListener = new LostFightButtonListener();

    private static final WonAndLevelupFightButtonListener wonAndLevelupFightButtonListener = new WonAndLevelupFightButtonListener();


    public static void init() {
        ((Interactible)GUIManager.getPanel("fight")).addButtonListener(wonFightButtonListener, "won");
        ((Interactible)GUIManager.getPanel("fight")).addButtonListener(lostFightButtonListener, "lost");
        ((Interactible)GUIManager.getPanel("fight")).addButtonListener(wonAndLevelupFightButtonListener, "levelup");
    }



    public static Boolean hasPlayerWon() {
        return playerWon;
    }

    public static void startFight() {
        resetFightResult();

        // Handles for explicit objects info display e.g. player & enemy health
        Player player = MainApp.getPlayer();
        Enemy enemy = MainApp.getEnemy();

        // Setup who attacks first and the other one defends. Based on flipping a coin (50/50 chance).
        chooseAttackerAndDefender();

        System.out.println("Fight!");
        while (attacker.getHealth() > 0 & defender.getHealth() > 0) {
            message = "Now " + attacker.getName() + " hits...";
            ((FightPanel)GUIManager.getPanel("fight")).setMessage(message);
            GUIManager.getPanel("fight").revalidate();
            MainApp.getGameFrame().repaint();
            try {
                Thread.sleep(1500);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }

            if (hasDefenderDodged()) {
                message = defender.getName() + " has dodged the attack!";
            } else {
                dealDamage();
            }
            if( defender instanceof Player)
                ((StatsPanel)GUIManager.getPanel("playerStats")).updateStats();
            else
                ((StatsPanel)GUIManager.getPanel("enemyStats")).updateStats();

            ((FightPanel)GUIManager.getPanel("fight")).setMessage(message);
            GUIManager.getPanel("fight").revalidate();
            MainApp.getGameFrame().repaint();
            System.out.println();
            switchAttacker();
        }

        setFightResult();
        if (hasPlayerWon()) {
           message = "Player won!";
        }
        else message = "Enemy won!";

        fightCleanup();
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
        if (dodgeGenerated < defender.getDodgeChance()) // e.g. if 35 is in [0, 70) == set representing probability = 70 %
        {
            return true;
        } else {
            return false;
        }
    }

    private static void dealDamage() {
        int damage = calculateDamage();
        damage -= defender.getArmor();

        if (damage > 0) {
            defender.setHealth(defender.getHealth() - damage);
            message += String.format("Boom! %s (%s) hits %s (%s) with %d damage!\n", attacker.getName(), attacker.getClass(), defender.getName(), defender.getClass(), damage);
        }
        else {
            message += String.format("%s (%s)'s armor has blocked the attack!\n", defender.getName(), defender.getClass());
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
        if (criticalGenerated < criticalChance) // e.g. if 35 is in [0, 70) == set representing probability = 70 %
        {
            return true;
        } else {
            return false;
        }
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
    }
}
