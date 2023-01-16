package data;

public class Weapon{
    private String name;
    private int tag;
    private int damage;
    private int criticalChance;
    private SpecialEffect special_effect = new SpecialEffect();


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }

    public Weapon(String name, int damage, int criticalChance) {
        this.name = name;
        this.damage = damage;
        this.criticalChance = criticalChance;
    }

    public Weapon() {
        this.name = "None";
        this.damage = 0;
        this.criticalChance = 0;
    }

}