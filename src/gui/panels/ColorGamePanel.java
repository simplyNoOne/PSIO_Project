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
        currentColor.setOpaque(true);
        currentColor.setBackground(new Color(0, 0, 0));
        currentColor.setForeground(new Color(255, 255, 255));
        currentColor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        expectedColor.setBounds(350, 10, 250, 200);
        expectedColor.setOpaque(true);
        expectedColor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

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

    public void setValueOnButton(String buttonColor, String value){
        int red = currentColor.getBackground().getRed();
        int green = currentColor.getBackground().getGreen();
        int blue = currentColor.getBackground().getBlue();
        switch (buttonColor){
            case "red" ->{
                redButton.setText(value);
                redButton.setEnabled(false);
                currentColor.setBackground(new Color(Integer.parseInt(value), green, blue));
                currentColor.setForeground(new Color(255-Integer.parseInt(value), 255-green, 255-blue));
            }
            case "green" -> {
                greenButton.setText(value);
                greenButton.setEnabled(false);
                currentColor.setBackground(new Color(red, Integer.parseInt(value), blue));
                currentColor.setForeground(new Color(255-red, 255-Integer.parseInt(value), 255-blue));

            }
            case "blue" -> {
                blueButton.setText(value);
                blueButton.setEnabled(false);
                currentColor.setBackground(new Color(red, green, Integer.parseInt(value)));
                currentColor.setForeground(new Color(255-red, 255-green, 255-Integer.parseInt(value)));

            }
        }

        repaint();
    }

    public void showValueOnButton(String buttonColor, String value){
        switch (buttonColor){
            case "red" ->{
                redButton.setText(value);
                redButton.setBackground(new Color(Integer.parseInt(value), 0, 0));
                redButton.setForeground(new Color(255-Integer.parseInt(value), 0, 0));
            }
            case "green" -> {
                greenButton.setText(value);
                greenButton.setBackground(new Color(0, Integer.parseInt(value), 0));
                greenButton.setForeground(new Color(0, 255-Integer.parseInt(value), 0));
            }
            case "blue" -> {
                blueButton.setText(value);
                blueButton.setBackground(new Color(0, 0, Integer.parseInt(value)));
                blueButton.setForeground(new Color(0, 0, 255-Integer.parseInt(value)));

            }
        }
        repaint();
    }
    public void setExpectedColor(Color color){
        expectedColor.setBackground(color);
        expectedColor.setForeground(new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue()));
    }

    public Color getExpectedColor(){
        return expectedColor.getBackground();
    }

    public Color getCurrentColor() {
        return currentColor.getBackground();
    }

    public void setColorButtonIsEnabled(String colorButtonName, boolean isEnabled){
        switch (colorButtonName){
            case "red" -> redButton.setEnabled(isEnabled);
            case "green" -> greenButton.setEnabled(isEnabled);
            case "blue" -> blueButton.setEnabled(isEnabled);
        }
    }

    public void resetPanel(){
        redButton.setEnabled(true);
        greenButton.setEnabled(true);
        blueButton.setEnabled(true);
        currentColor.setBackground(new Color(0,0,0));
        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        redButton.setText("");
        greenButton.setText("");
        blueButton.setText("");
    }
}