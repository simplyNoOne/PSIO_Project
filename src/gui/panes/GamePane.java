package gui.panes;

import gui.panes.CustomLayerPane;
import managers.GUIManager;

public class GamePane extends CustomLayerPane {

    public GamePane(){
        getLayeredPane().setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());
    }
}
