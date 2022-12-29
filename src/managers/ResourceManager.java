package managers;

import data.Texture;

import java.util.HashMap;

public class ResourceManager {

    private static HashMap<String, Texture> textures = new HashMap<>(); //name -> texture

    public static void loadResources(){

        addTexture("background", new Texture("resources\\textures\\Background.png"));

        //weapons in color
        addTexture("axe", new Texture("resources\\textures\\axe.png"));
        addTexture("sword", new Texture("resources\\textures\\sword.png"));
        addTexture("dagger", new Texture("resources\\textures\\dagger.png"));

        //weapons in gray
        addTexture("grayAxe", new Texture("resources\\textures\\gAxe.png"));
        addTexture("graySword", new Texture("resources\\textures\\gSword.png"));
        addTexture("grayDagger", new Texture("resources\\textures\\gDagger.png"));


        //collectibles in color
        addTexture("col1", new Texture("resources\\textures\\collectible1.png"));
        addTexture("col2",new Texture("resources\\textures\\collectible2.png"));
        addTexture("col3", new Texture("resources\\textures\\collectible3.png"));
        addTexture("col4",new Texture("resources\\textures\\collectible4.png"));
        addTexture("col5", new Texture("resources\\textures\\collectible5.png"));

        //collectibles grayed out
        addTexture("grayCol1", new Texture("resources\\textures\\collect1.png"));
        addTexture("grayCol2",new Texture("resources\\textures\\collect2.png"));
        addTexture("grayCol3", new Texture("resources\\textures\\collect3.png"));
        addTexture("grayCol4",new Texture("resources\\textures\\collect4.png"));
        addTexture("grayCol5", new Texture("resources\\textures\\collect5.png"));


        //buttons, will probably get rid of it
        addTexture("confirm1", new Texture("resources\\textures\\confirm1.png"));
        addTexture("confirm2", new Texture("resources\\textures\\confirm2.png"));
        addTexture("carry_out_the_battle", new Texture("resources\\textures\\carry_out_the_fight.png"));
        addTexture("fight", new Texture("resources\\textures\\fight.png"));
        addTexture("puzzle", new Texture("resources\\textures\\puzzle.png"));
        addTexture("ok", new Texture("resources\\textures\\ok.png"));


        //characters
        addTexture("enemy1", new Texture("resources\\textures\\enemy1.png"));
        addTexture("enemy2", new Texture("resources\\textures\\enemy2.png"));
        addTexture("boss", new Texture("resources\\textures\\boss.png"));
        addTexture("player", new Texture("resources\\textures\\player.png"));


        //stats? are those even needed?
        addTexture("stat0", new Texture("resources\\textures\\stat0.png"));

        addTexture("stat1", new Texture("resources\\textures\\stat1.png"));

        addTexture("stat2", new Texture("resources\\textures\\stat2.png"));


        addTexture("ok", new Texture("resources\\textures\\ok.png"));
        addTexture("correct", new Texture("resources\\textures\\correct.png"));
        addTexture("wrong", new Texture("resources\\textures\\wrong.png"));

        addTexture("won", new Texture("resources\\textures\\won.png"));
        addTexture("lost", new Texture("resources\\textures\\lost.png"));
        addTexture("levelup", new Texture("resources\\textures\\levelup.png"));



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
