package data;


import managers.ResourceManager;

public class Enemy extends Character
{
    private static final int START_POS_X = 2460;
    public static final int  MOVE_SPEED = 170;
    private boolean canPuzzle;
    private String abilityName;
    public Texture texture;

    public Enemy() {
        super();
        this.canPuzzle =  false;
        this.abilityName = "None";

        if (Math.random()*2 > 1)
            texture = ResourceManager.getTexture("enemy2");
        else
            texture = ResourceManager.getTexture("enemy1");
        getLocation().x = START_POS_X;
        getLocation().y = 350;
       

    }

    public Enemy(String name, int health, int armor, int baseDamage, int dodgeChance, boolean canPuzzle, String abilityName, int criticalChance)
    {
        super(name, health, armor, baseDamage, dodgeChance, criticalChance);
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

    public void moveEnemy(double distance){
        getLocation().x -= distance;
    }
}
