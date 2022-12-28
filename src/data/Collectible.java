package data;

public class Collectible {
    private String name;
    private int tag;
    private Special_Effect special_effect = new Special_Effect();


    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collectible(String name) {
        this.name = name;
    }

    public Collectible() {
        this.name = "None";
    }


}

