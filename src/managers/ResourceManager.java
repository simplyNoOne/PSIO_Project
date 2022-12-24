package managers;

import data.Texture;

import java.util.HashMap;

public class ResourceManager {

    private static HashMap<String, Texture> textures = new HashMap<>(); //name -> texture

    public static void loadResources(){

        addTexture("hammer", new Texture("resources\\textures\\hammer.png"));

        addTexture("sword", new Texture("resources\\textures\\sword.png"));


        addTexture("health_potion", new Texture("resources\\textures\\health_potion.png"));

        addTexture("throwable_poison",new Texture("resources\\textures\\throwable_poison.png"));

        addTexture("sultans_fart", new Texture("resources\\textures\\sultans_fart.png"));


        addTexture("confirm1", new Texture("resources\\textures\\confirm1.png"));

        addTexture("confirm2", new Texture("resources\\textures\\confirm2.png"));

        addTexture("carry_out_the_battle", new Texture("resources\\textures\\carry_out_the_fight.png"));

        addTexture("fight", new Texture("resources\\textures\\fight.png"));

        addTexture("puzzle", new Texture("resources\\textures\\puzzle.png"));


        addTexture("stat1", new Texture("resources\\textures\\stat1.png"));

        addTexture("stat2", new Texture("resources\\textures\\stat2.png"));

        addTexture("stat3", new Texture("resources\\textures\\stat3.png"));


        addTexture("ok", new Texture("resources\\textures\\ok.png"));
        addTexture("correct", new Texture("resources\\textures\\correct.png"));
        addTexture("wrong", new Texture("resources\\textures\\wrong.png"));
    }

    public static void unloadResources(){
        textures.clear();
    }

    public static void addTexture(String textureName, Texture texture) {
        textures.put(textureName, texture);
    }
    public static void removeTexture(String textureName)
    {
        textures.remove(textureName);
    }

    public static Texture getTexture(String textureName) {return textures.get(textureName); }
    public static void setTextures(String textureName, Texture texture) { textures.replace(textureName, texture);}
}
