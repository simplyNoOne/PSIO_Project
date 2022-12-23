package data;

import java.util.HashMap;
import java.util.*;
import managers.*;
public class Enemy extends Character
{
    private boolean canPuzzle;
    private String abilityName;
    Map <String , Texture> enemy_textures = new HashMap<>();
    ResourceManager resources = new ResourceManager();

    public Enemy() {
        super();
        this.canPuzzle =  false;
        this.abilityName = "None";
        add_enemy_texture();

    }

    public Enemy(String name, int health, int armor, int baseDamage, int dodgeChance, boolean canPuzzle, String abilityName)
    {
        super(name, health, armor, baseDamage, dodgeChance);
        this.canPuzzle = canPuzzle;
        this.abilityName = abilityName;

    }

    private void add_enemy_texture(){
        enemy_textures.put("default" , resources.getTexture("enemy"));
    }

    public Texture getTexture(String value){
        return enemy_textures.get(value);
    }
    


    public Enemy getEnemy(){
        return this;
    }

    public boolean isCanPuzzle() {
        return canPuzzle;
    }

    public void setCanPuzzle(boolean canPuzzle) {
        this.canPuzzle = canPuzzle;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
