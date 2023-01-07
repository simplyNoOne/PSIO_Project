package data;

import main.*;
public class Collectible {
    private String name;
    


    public void increase_health(Player player){
        if(player.getHealth()*1.2>=player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
        else player.setHealth((int) Math.round(player.getHealth()*1.2));
    }


    public void increase_damage(Player player){
        player.setBaseDamage((int) Math.round(player.getBaseDamage()*1.2));
    }

    public void skip_level(){
        StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
    }

    public void increase_dodge(Player player){
        player.setDodgeChance((int) Math.round(player.getDodgeChance()*1.2));
    }

    public void increase_critical(Player player){
        player.setCriticalChance((int) Math.round(player.getCriticalChance()*1.2));
    }


    public String getName() {
        return name;
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

