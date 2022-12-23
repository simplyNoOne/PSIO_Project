package data;

import java.util.*;

import data.Character;
import data.Inventory;
import managers.ResourceManager;

public class Player extends Character {
    private Inventory inventory = new Inventory();
    ArrayList <Texture> player_move_textures = new ArrayList();
    ResourceManager resources = new ResourceManager();

    //TODO arg constructor

    public Player(){
        super();
        add_player_texture();


    }




    private void add_player_texture(){
        player_move_textures.add(resources.getTexture("player"));
    }

    public Texture getTexture(int num){
        return player_move_textures.get(num);
    }
    

    public Inventory getInventory() {
        return inventory;
    }
    



    
}
