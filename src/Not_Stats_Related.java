public class Not_Stats_Related extends Collectible{
    private String effect_name;

    public Not_Stats_Related() {
        this.effect_name = "None";
    }

    public Not_Stats_Related(String name, int collectible_chance, String effect_name) {
        super(name, collectible_chance);
        this.effect_name = effect_name;
    }

    public String getEffect_name() {
        return effect_name;
    }

    public void setEffect_name(String effect_name) {
        this.effect_name = effect_name;
    }
}
