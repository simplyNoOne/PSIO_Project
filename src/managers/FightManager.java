package managers;

import data.Character;
import data.Enemy;
import data.Player;
import gui.panels.FightPanel;
import gui.panels.StatsPanel;
import interfaces.ScoreModifier;
import main.MainApp;
import main.ManagerHandler;
import main.StateMachine;
import java.util.Random;

public class FightManager implements ScoreModifier {


    private String message; // fight round info displayed in GUI, like "Player hits enemy..."
    private Character attacker; // abstract handle, can change between Player and Enemy. Convenient to use as a class field
    private Character defender; // the other character
    private Boolean playerWon = null; // Fight result. Wrapping as a non-primitive boolean gives possibility to set result as null when it's unknown

    private boolean prevFightWon;       //stores the result of the fight, to be accessed by fight results
    private int numPlayerAttacks;

    private static final int DAMAGE_RANDOMIZATION_PERCENT = 10; // Damage slightly randomized e.g. base hit 100 will really be a random from 90 ... 110
    private static final int CRITICAL_MULTIPLIER = 2; // 2x (double) damage in case of a critical hit
    private static final int PHASE_DELAY_SECS = 2; // time interval between next phases in round (setup attacker, damage). Needed to update GUI for the user

    private boolean wait = false;       //makes sure that refreshing the screen doesn't happen while changing message text
    private boolean isRoundStarting = true;
    private double timeSinceLastPhase; // time between FightManager's calls. Phases of each round: 1) preparing, 2) damage

   

    public void init() {
        message = "Opponents approach each other...";
        attacker = null;
        defender = null;
        playerWon = null;

        isRoundStarting = true;
        timeSinceLastPhase = 0;

        ((FightPanel) ManagerHandler.getGUIManager().getPanel("fight")).setMessage(message);
    }


    public void attemptToFightRound(double deltaTime) {
        if(!wait)
            MainApp.getGameFrame().repaint();
        timeSinceLastPhase += deltaTime;
        if (timeSinceLastPhase > PHASE_DELAY_SECS) {
            timeSinceLastPhase = 0;

            if (shouldFightContinue()) {
                fightRound();
            } else {
                finishFight();
            }
        }
    }

    public boolean shouldFightContinue() {
        if (playerWon == null) {
            return true;
        }
        return false;
    }

    private void fightRound() {
        if (isRoundStarting) {
            prepareRound();
        }
        else { // damage time
            enterCombat();
        }
    }

    private void prepareRound() {
        if (isFightInitialized()) {
            switchAttacker();
        }
        else { // fight hasn't even started before (= 1st round!)
            numPlayerAttacks = 0;
           chooseAttackerAndDefender();
        }

        isRoundStarting = false; // round is set up and damage ready to be dealt
        message = "Now " + attacker.getName() + " charges...";
        wait = true;
        ((FightPanel) ManagerHandler.getGUIManager().getPanel("fight")).setMessage(message);
        MainApp.getGameFrame().repaint();
        wait = false;
    }


    private boolean isFightInitialized() {
        if (attacker == null || defender == null)
            return false;
        return true;
    }

    // swap attacker and defender
    private void switchAttacker() {
        Character temp = attacker;
        attacker = defender;
        defender = temp;
    }

    private void chooseAttackerAndDefender() {
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

    private void enterCombat() {
        message = "";

        if (hasDefenderDodged()) {
            message = defender.getName() + " has dodged the attack!";
        } else {
            dealDamage();
        }

        if (defender instanceof Player)
            ((StatsPanel) ManagerHandler.getGUIManager().getPanel("playerStats")).updateStats();
        else {
            ((StatsPanel) ManagerHandler.getGUIManager().getPanel("enemyStats")).updateStats();
            numPlayerAttacks++;
        }
        wait = true;
        ((FightPanel) ManagerHandler.getGUIManager().getPanel("fight")).setMessage(message);
        MainApp.getGameFrame().repaint();
        wait = false;
        setFightResult();
        isRoundStarting = true;
    }

    /**
     * TODO best to create new small class e.g. RandomEventManager with method: public boolean hasProbabilisticEventOccurred(int eventChance) -> used for critical hit, dodge etc.
     **/
    private boolean hasDefenderDodged() {
        // dodgeChance: 0 ... 100 %

        if (defender.getDodgeChance() == 0)
            return false;
        else if (defender.getDodgeChance() == 100)
            return true;

        int dodgeGenerated = (new Random()).nextInt(100);
        return (dodgeGenerated < defender.getDodgeChance()); // e.g. if 35 is in [0, 70) == set representing probability = 70 %

    }

    private void dealDamage() {
        int damage = calculateDamage();
        damage -= defender.getArmor();

        if (damage > 0) {
            defender.setHealth(defender.getHealth() - damage);
            String temp = String.format("%s hits %s with %d damage!", attacker.getName(),  defender.getName(), damage);
            message +="Boom! " + System.lineSeparator();
            message += temp;

        }
        else {
            message += String.format("%s 's armor has blocked the attack!", defender.getName());
        }
    }

    private int calculateDamage() {
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

    private boolean isCriticalAttack() {
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
    private void setFightResult() {
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

    private void finishFight(){
        prevFightWon = playerWon;
        if (playerWon) {
            if(MainApp.getEnemy().getIsBoss())
                StateMachine.setNextStateVar(StateMachine.State.LEVELUP);
            else
                StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);

        } else
            StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);

        init(); // needed for cleanup, resets FightManager for next fights
        MainApp.getPlayer().resetActiveWeapon(); // un-equip weapon after fight
        StateMachine.nextState();
    }

    public boolean getPrevFightWon(){return prevFightWon;}

    @Override
    public int getScoreModifier() {
        if(MainApp.getPlayer().getHealth() > 0)
            return MainApp.getEnemy().getInitialHealth() + MainApp.getEnemy().getScoreModifier()/numPlayerAttacks;
        else
            return 0;
    }
}
