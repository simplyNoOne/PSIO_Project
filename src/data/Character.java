package data;

public abstract class Character
{
    private String name;
    private int maxHealth;
    private int armor;
    private int baseDamage;
    private int criticalChance; //%
    private int dodgeChance;

    

    public Character(String name, int maxHealth, int armor, int baseDamage, int dodgeChance, int criticalChance) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.armor = armor;
        this.baseDamage = baseDamage;
        this.dodgeChance = dodgeChance;
        this.criticalChance = criticalChance;
    }

    public Character() {
        this.name = "None";
        this.maxHealth = 0;
        this.armor = 0;
        this.baseDamage = 0;
        this.dodgeChance = 0;
        this.criticalChance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getCriticalChance() {
         return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }
}
