package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;

public class ColorGamePanel extends CustomPanel implements Interactible {
    @Override
    public void addButtonListener(ActionListener listener, String buttonId) {

    }

    public class CheckButton extends JButton {

        private static final int BUTTON_WIDTH = 150;
        private static final int BUTTON_HEIGHT = 150;
        CheckButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        CheckButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
        }

    }

    public class ColorButton extends JButton {

        private static final int BUTTON_WIDTH = 100;
        private static final int BUTTON_HEIGHT = 256;
        ColorButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);
            this.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        ColorButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
        }

    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private ColorButton redButton = new ColorButton();
    private ColorButton greenButton = new ColorButton();
    private ColorButton blueButton = new ColorButton();
    private JLabel currentColor = new JLabel("Current");
    private JLabel expectedColor = new JLabel("Expected");
    public ColorGamePanel(){


        super();
        this.setBackground(new Color(255, 255, 255));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        redButton.setLocation(100, 220);
        redButton.setBackground(Color.RED);
        redButton.setName("red");
        greenButton.setLocation(300, 220);
        greenButton.setBackground(Color.GREEN);
        greenButton.setName("green");
        blueButton.setLocation(500, 220);
        blueButton.setBackground(Color.BLUE);
        blueButton.setName("blue");

        currentColor.setBounds(100, 10, 250, 200);
        expectedColor.setBounds(350, 10, 250, 200);

        this.add(currentColor);
        this.add(expectedColor);
        this.add(redButton);
        this.add(greenButton);
        this.add(blueButton);

    }


    public void addMouseListener(MouseAdapter adapter, String buttonId){
        switch (buttonId){
            case "red" -> redButton.addMouseListener(adapter);
            case "green" -> greenButton.addMouseListener(adapter);
            case "blue" -> blueButton.addMouseListener(adapter);
        }
    }

    public void showValueOnButton(String buttonColor, String value){
        int red = currentColor.getBackground().getRed();
        int green = currentColor.getBackground().getGreen();
        int blue = currentColor.getBackground().getBlue();
        switch (buttonColor){
            case "red" ->{
                redButton.setText(value);
                redButton.setEnabled(false);
                currentColor.setBackground(new Color(Integer.parseInt(value), green, blue));
            }
            case "green" -> {
                greenButton.setText(value);
                greenButton.setEnabled(false);
                currentColor.setBackground(new Color(red, Integer.parseInt(value), blue));
            }
            case "blue" -> {
                blueButton.setText(value);
                blueButton.setEnabled(false);
                currentColor.setBackground(new Color(red, green, Integer.parseInt(value)));
            }
        }
        repaint();
    }
    public void setExpectedColor(Color color){
        expectedColor.setBackground(color);
    }

    public Color getExpectedColor(){
        return expectedColor.getBackground();
    }


}