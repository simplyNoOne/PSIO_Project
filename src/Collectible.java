public abstract class Collectible {
    private String name;
    private int collectible_chance;

    public Collectible() {
        this.name = "None";
        this.collectible_chance = 0;
    }

    public Collectible(String name, int collectible_chance) {
        this.name = name;
        this.collectible_chance = collectible_chance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCollectible_chance() {
        return collectible_chance;
    }

    public void setCollectible_chance(int collectible_chance) {
        this.collectible_chance = collectible_chance;
    }
}
