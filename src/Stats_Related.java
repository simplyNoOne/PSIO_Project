public class Stats_Related extends Collectible{
    private int bonus_amount;
    private String stats_name;

    public Stats_Related() {
        this.bonus_amount = 0;
        this.stats_name = "None";
    }

    public Stats_Related(String name, int collectible_chance, int bonus_amount, String stats_name) {
        super(name, collectible_chance);
        this.bonus_amount = bonus_amount;
        this.stats_name = stats_name;
    }

    public int getBonus_amount() {
        return bonus_amount;
    }

    public void setBonus_amount(int bonus_amount) {
        this.bonus_amount = bonus_amount;
    }

    public String getStats_name() {
        return stats_name;
    }

    public void setStats_name(String stats_name) {
        this.stats_name = stats_name;
    }
}
