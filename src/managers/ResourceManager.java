package managers;

import data.Texture;

import java.io.File;
import java.util.HashMap;

public class ResourceManager {

    private HashMap<String, Texture> textures = new HashMap<>(); //name -> texture

    public void loadResources(){

        addTexture("background", new Texture("resources" + File.separator + "textures" + File.separator + "Background.png"));

        //weapons in color
        addTexture("axe", new Texture("resources" + File.separator + "textures" + File.separator +  "axe.png"));
        addTexture("sword", new Texture("resources" + File.separator + "textures" + File.separator +  "sword.png"));
        addTexture("dagger", new Texture("resources" + File.separator  + "textures" + File.separator + "dagger.png"));

        //weapons in gray
        addTexture("grayAxe", new Texture("resources" + File.separator + "textures" + File.separator + "gAxe.png"));
        addTexture("graySword", new Texture("resources" + File.separator + "textures" + File.separator +  "gSword.png"));
        addTexture("grayDagger", new Texture("resources" + File.separator + "textures" + File.separator +  "gDagger.png"));


        //collectibles in color
        addTexture("col1", new Texture("resources" + File.separator + "textures" + File.separator +  "collectible1.png"));
        addTexture("col2",new Texture("resources" + File.separator + "textures" + File.separator +  "collectible2.png"));
        addTexture("col3", new Texture("resources" + File.separator + "textures" + File.separator +  "collectible3.png"));
        addTexture("col4",new Texture("resources" + File.separator + "textures" + File.separator +  "collectible4.png"));
        addTexture("col5", new Texture("resources" + File.separator + "textures" + File.separator +  "collectible5.png"));

        //collectibles grayed out
        addTexture("grayCol1", new Texture("resources" + File.separator + "textures" + File.separator +  "collect1.png"));
        addTexture("grayCol2",new Texture("resources" + File.separator + "textures" + File.separator +  "collect2.png"));
        addTexture("grayCol3", new Texture("resources" + File.separator + "textures" + File.separator +  "collect3.png"));
        addTexture("grayCol4",new Texture("resources" + File.separator + "textures" + File.separator +  "collect4.png"));
        addTexture("grayCol5", new Texture("resources" + File.separator + "textures" + File.separator +  "collect5.png"));


        //buttons, will probably get rid of it
        addTexture("confirm1", new Texture("resources" + File.separator + "textures" + File.separator +  "confirm1.png"));
        addTexture("confirm2", new Texture("resources" + File.separator + "textures" + File.separator +  "confirm2.png"));
        addTexture("carry_out_the_battle", new Texture("resources" + File.separator + "textures" + File.separator +  "carry_out_the_fight.png"));
        addTexture("fight", new Texture("resources" + File.separator + "textures" + File.separator +  "fight.png"));
        addTexture("puzzle", new Texture("resources" + File.separator + "textures" + File.separator +  "puzzle.png"));
        addTexture("ok", new Texture("resources" + File.separator + "textures" + File.separator +  "ok.png"));


        //characters
        addTexture("enemy1", new Texture("resources" + File.separator + "textures" + File.separator +  "enemy1.png"));
        addTexture("enemy2", new Texture("resources" + File.separator + "textures" + File.separator +  "enemy2.png"));
        addTexture("boss", new Texture("resources" + File.separator + "textures" + File.separator +  "boss.png"));
        addTexture("player", new Texture("resources" + File.separator + "textures" + File.separator +  "player.png"));


        //stats? are those even needed?
        addTexture("stat0", new Texture("resources" + File.separator + "textures" + File.separator +  "stat0.png"));

        addTexture("stat1", new Texture("resources" + File.separator + "textures" + File.separator +  "stat1.png"));

        addTexture("stat2", new Texture("resources" + File.separator + "textures" + File.separator +  "stat2.png"));


        addTexture("ok", new Texture("resources" + File.separator + "textures" + File.separator +  "ok.png"));
        addTexture("correct", new Texture("resources" + File.separator + "textures" + File.separator +  "correct.png"));
        addTexture("wrong", new Texture("resources" + File.separator + "textures" + File.separator +  "wrong.png"));

        addTexture("won", new Texture("resources" + File.separator + "textures" + File.separator +  "won.png"));
        addTexture("lost", new Texture("resources" + File.separator + "textures" + File.separator +  "lost.png"));
        addTexture("levelup", new Texture("resources" + File.separator + "textures" + File.separator +  "levelup.png"));

    }

    public void unloadResources(){
        textures.clear();
    }

    public void addTexture(String textureName, Texture texture) {
        textures.put(textureName, texture);
    }
    public void removeTexture(String textureName)
    {
        textures.remove(textureName);
    }

    public Texture getTexture(String textureName) {return textures.get(textureName); }
    public void setTextures(String textureName, Texture texture) { textures.replace(textureName, texture);}
}
