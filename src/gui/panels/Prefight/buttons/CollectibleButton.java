package gui.panels.Prefight.buttons;

import data.Texture;
import gui.CustomButton;

public class CollectibleButton extends CustomButton {

    private String collectibleName;
    private Texture collectibleTexture;

    public CollectibleButton() {super();}
    public CollectibleButton(String name, Texture texture) {
        super(texture);
        collectibleTexture = texture;
        collectibleName = name;
    }

    public String getCollectibleName() {
        return collectibleName;
    }

    public Texture getCollectibleTexture() {
        return collectibleTexture;
    }

    public void setCollectibleName(String name) {
        collectibleName = name;
    }

    public void setCollectibleTexture(Texture texture) {
        collectibleTexture = texture;
    }

}
