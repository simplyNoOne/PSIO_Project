public class Enemy extends Character
{
    private boolean canPuzzle;
    private String abilityName;

    public Enemy() {
        super();
        this.canPuzzle =  false;
        this.abilityName = "None";

    }

    public Enemy(String name, int health, int armor, int baseDamage, int dodgeChance, boolean canPuzzle, String abilityName)
    {
        super(name, health, armor, baseDamage, dodgeChance);
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
