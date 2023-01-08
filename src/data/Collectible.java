package data;

import main.*;
public class Collectible {
    private String name;
    private double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collectible(String name , double value) {
        this.name = name;
        this.value = value;
    }

    public Collectible() {
        this.name = "None";
    }


    public double getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }


}

