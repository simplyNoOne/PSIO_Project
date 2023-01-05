package data;


import managers.ResourceManager;

public class Enemy extends Character
{
    private static final int START_POS_X = 2335;
    private boolean isBoss;
    private String abilityName;
    public Texture texture;

    public Enemy() {
        super();
        this.isBoss =  false;
        this.abilityName = "None";

        if (Math.random()*2 > 1)
            texture = ResourceManager.getTexture("enemy2");
        else
            texture = ResourceManager.getTexture("enemy1");
        getLocation().x = START_POS_X;
        getLocation().y = 350;
       

    }

    public Enemy(String name, int health, int armor, int baseDamage, int dodgeChance, boolean isBoss, String abilityName, int criticalChance, Texture texture)
    {
        super(name, health, armor, baseDamage, dodgeChance, criticalChance);
        this.isBoss = isBoss;
        this.abilityName = abilityName;
        this.texture = texture;
        getLocation().x = START_POS_X;
        getLocation().y = 350;
    }

    public Texture getEnemyTexture() {
        return texture;

    }

    public void setEnemyTexture(Texture texture){this.texture = texture;}


    


    public Enemy getEnemy(){
        return this;
    }

    public boolean getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
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
