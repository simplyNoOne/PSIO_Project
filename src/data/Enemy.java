package data;
public class Enemy extends Character
{
    private boolean canPuzzle;
    private String abilityName;

    public Enemy() {
        super();
        this.canPuzzle =  false;
        this.abilityName = "None";

    }

    public Enemy(String name, int maxHealth, int armor, int baseDamage, int dodgeChance, boolean canPuzzle, String abilityName, int criticalChance)
    {
        super(name, maxHealth, armor, baseDamage, dodgeChance, criticalChance);
        this.canPuzzle = canPuzzle;
        this.abilityName = abilityName;

    }

    public boolean isCanPuzzle() {
        return canPuzzle;
    }

    public void setCanPuzzle(boolean canPuzzle) {
        this.canPuzzle = canPuzzle;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}