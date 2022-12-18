package gui.panes;

import gui.panels.CustomPanel;

import javax.swing.*;

abstract public class CustomLayerPane extends JPanel{
    int layers = 0;
    private JLayeredPane layeredPane  = new JLayeredPane();

    public CustomLayerPane(){
        this.setLayout(null);
        this.add(layeredPane);
    }

    public JLayeredPane getLayeredPane(){
        return layeredPane;
    }
    public void addLayer(CustomPanel panel){
        layeredPane.add(panel, Integer.valueOf(layers));
        layers++;
    }

    public void addLayer(CustomPanel panel, int layer){
        layeredPane.add(panel, Integer.valueOf(layer));
    }

    public void removeLayer(CustomPanel panel){
        layeredPane.remove(panel);
        layers--;
    }

}
