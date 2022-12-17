package gui.panels.PuzzleOrFight.buttons;

import controller.Managers.Texture;
import gui.CustomButton;

public class ChoiceButton extends CustomButton {

    private String choiceValue;

    public ChoiceButton(){
        super();
    }

    public ChoiceButton(String value, Texture texture){
        super(texture);
        choiceValue = value;
    }

    public String getChoiceValue(){
        return choiceValue;
    }
}
