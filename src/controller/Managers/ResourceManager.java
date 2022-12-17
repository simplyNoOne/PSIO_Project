package controller.Managers;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {

    private static HashMap<String, Texture> textures = new HashMap<>(); //name -> texture

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
