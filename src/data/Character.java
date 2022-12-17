package data;

public abstract class Character
{
    private String name;
    private int health;
    private int armor;
    private int baseDamage;
    //TODO private int criticalChance;
    private int dodgeChance;

    

    public Character(String name, int health, int armor, int baseDamage, int dodgeChance) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.baseDamage = baseDamage;
        this.dodgeChance = dodgeChance;
    }

    public Character() {
        this.name = "None";
        this.health = 0;
        this.armor = 0;
        this.baseDamage = 0;
        this.dodgeChance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    //TODO public int getCriticalChance() {
    //     return criticalChance;
    // }

    // public void setCriticalChance(int criticalChance) {
    //     this.criticalChance = criticalChance;
    // }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }
}
