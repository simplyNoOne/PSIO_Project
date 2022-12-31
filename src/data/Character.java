package data;

public abstract class Character
{
    private String name;
    private int health;
    private int armor;
    private int baseDamage;
    private int criticalChance;
    private int dodgeChance;

    private Vector2 location;
    

    

    public Character(String name, int health, int armor, int baseDamage, int dodgeChance, int criticalChance) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.baseDamage = baseDamage;
        this.dodgeChance = dodgeChance;
        this.criticalChance = criticalChance;
    }

    public Character() {
        this.location = new Vector2();
        this.name = "LongNoneName";
        this.health = 100;
        this.armor = 50;
        this.baseDamage = 100;
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

    public Vector2 getLocation(){return location;}

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
