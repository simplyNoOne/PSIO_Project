package gui.panels.Prefight.buttons;

import data.Texture;
import gui.CustomButton;

public class WeaponButton extends CustomButton {

    private String weaponName;
    private Texture weaponTexture;

    public WeaponButton() {super();}
    public WeaponButton(String name, Texture texture) {
        super(texture);
        weaponTexture = texture;
        weaponName = name;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public Texture getWeaponTexture() {
        return weaponTexture;
    }

    public void setWeaponName(String weaponName) {
        weaponName = weaponName;
    }

    public void setWeaponTexture(Texture weaponTexture) {
        weaponTexture = weaponTexture;
    }

}