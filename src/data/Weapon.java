package data;

import data.Special_Effect;

public class Weapon{
    private String name;
    private int Tag;
    private double damage;
    private Special_Effect special_effect = new Special_Effect();


    public double getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return Tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon(String name, double damage) {
        this.name = name;
        this.damage = damage;
    }

    public Weapon() {
        this.name = "None";
        this.damage = 0;
    }


    
}