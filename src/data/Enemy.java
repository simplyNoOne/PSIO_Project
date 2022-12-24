package data;


import managers.ResourceManager;

public class Enemy extends Character
{
    private static final int START_POS_X = 2460;
    private static final int  MOVE_SPEED = 160;
    private boolean canPuzzle;
    private String abilityName;
    public Texture texture;

    public Enemy() {
        super();
        this.canPuzzle =  false;
        this.abilityName = "None";

        texture = ResourceManager.getTexture("enemy");
        getLocation().x = START_POS_X;
        getLocation().y = 400;
       

    }

    public Enemy(String name, int health, int armor, int baseDamage, int dodgeChance, boolean canPuzzle, String abilityName)
    {
        super(name, health, armor, baseDamage, dodgeChance);
        this.canPuzzle = canPuzzle;
        this.abilityName = abilityName;
        texture = ResourceManager.getTexture("enemy");

    }

    public Texture getEnemyTexture() {
        return texture;

    }

    public void setEnemyTexture(Texture texture){this.texture = texture;}


    


    public Enemy getEnemy(){
        return this;
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

    public void moveEnemy(double dT){
        getLocation().x -= dT* MOVE_SPEED;
    }
}
