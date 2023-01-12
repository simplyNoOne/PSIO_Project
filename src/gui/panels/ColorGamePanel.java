package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;

public class ColorGamePanel extends CustomPanel implements Interactible {

    public class ConfirmButton extends JButton {

        private static final int BUTTON_WIDTH = 150;
        private static final int BUTTON_HEIGHT = 150;
        ConfirmButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
    }

    public class JSliderUI extends BasicSliderUI {

        public JSliderUI(JSlider slider) {
            super(slider);
        }

        @Override
        public void paintFocus(Graphics grphcs) {

        }

        @Override
        protected Dimension getThumbSize() {
            return new Dimension(14, 14);
        }

        @Override
        public void paintThumb(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(slider.getForeground());
            g2.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        public void paintTrack(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(slider.getBackground());
            if (slider.getOrientation() == JSlider.VERTICAL) {
                g2.fillRoundRect(slider.getWidth() / 2 - 3, 2, 4, slider.getHeight(), 1, 1);
            } else {
                g2.fillRoundRect(2, slider.getHeight() / 2 - 2, slider.getWidth() - 5, 4, 1, 1);
            }
        }
    }

    public class JsliderCustom extends JSlider {

        public JsliderCustom() {
            setOpaque(false);
            setBackground(new Color(180, 180, 180));
            setForeground(new Color(69, 124, 235));
            setUI(new JSliderUI(this));
        }
    }



    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private JLabel currentColor = new JLabel();
    private JLabel currentColorText = new JLabel("Current color");
    private JLabel expectedColor = new JLabel();
    private JLabel expectedColorText = new JLabel("Expected");
    private JsliderCustom redSlider = new JsliderCustom();
    private JsliderCustom greenSlider = new JsliderCustom();
    private JsliderCustom blueSlider = new JsliderCustom();
    private JLabel redColorPreview = new JLabel();
    private JLabel greenColorPreview = new JLabel();
    private JLabel blueColorPreview = new JLabel();
    private JLabel leftChancesLabel = new JLabel();
    private ConfirmButton confirmButton = new ConfirmButton();
    public ColorGamePanel(){


        super();
        this.setBackground(new Color(0, 0, 0, 220));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        redSlider.setOrientation(JSlider.VERTICAL);
        redSlider.setMinimum(0);
        redSlider.setMaximum(255);
        redSlider.setValue(127);
        redSlider.setForeground(Color.RED);
        redSlider.setLocation(190, 250);
        redSlider.setSize(20, 125);
        redSlider.setName("red");
        this.add(redSlider);

        redColorPreview.setBounds(175, 180, 50, 50);
        redColorPreview.setOpaque(true);
        redColorPreview.setBackground(new Color(redSlider.getValue(), 0, 0));
        redColorPreview.setForeground(Color.WHITE);
        redColorPreview.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        redColorPreview.setFont(new Font("1", Font.PLAIN, 9));
        this.add(redColorPreview);

        greenSlider.setOrientation(JSlider.VERTICAL);
        greenSlider.setMinimum(0);
        greenSlider.setMaximum(255);
        greenSlider.setValue(127);
        greenSlider.setForeground(Color.GREEN);
        greenSlider.setLocation(340, 250);
        greenSlider.setSize(20, 125);
        greenSlider.setName("green");
        this.add(greenSlider);

        greenColorPreview.setBounds(325, 180, 50, 50);
        greenColorPreview.setOpaque(true);
        greenColorPreview.setBackground(new Color(0, greenSlider.getValue(), 0));
        greenColorPreview.setForeground(Color.WHITE);
        greenColorPreview.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        greenColorPreview.setFont(new Font("1", Font.PLAIN, 9));

        this.add(greenColorPreview);

        blueSlider.setOrientation(JSlider.VERTICAL);
        blueSlider.setMinimum(0);
        blueSlider.setMaximum(255);
        blueSlider.setValue(127);
        blueSlider.setForeground(Color.BLUE);
        blueSlider.setLocation(490, 250);
        blueSlider.setSize(20, 125);
        blueSlider.setName("blue");
        this.add(blueSlider);

        blueColorPreview.setBounds(475, 180, 50, 50);
        blueColorPreview.setOpaque(true);
        blueColorPreview.setBackground(new Color(0, 0, blueSlider.getValue()));
        blueColorPreview.setForeground(Color.WHITE);
        blueColorPreview.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        blueColorPreview.setFont(new Font("1", Font.PLAIN, 9));
        this.add(blueColorPreview);

        currentColor.setBounds(100, 50, 200, 100);
        currentColor.setOpaque(true);
        currentColor.setBackground(new Color(255, 255, 255));
        currentColor.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        this.add(currentColor);

        currentColorText.setBounds(100, 25, 200, 25);
        currentColorText.setForeground(new Color(255, 255, 255));
        this.add(currentColorText);

        expectedColor.setBounds(400, 50, 200, 100);
        expectedColor.setOpaque(true);
        expectedColor.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.add(expectedColor);

        expectedColorText.setBounds(400, 25, 200, 25);
        expectedColorText.setForeground(new Color(255, 255, 255));
        this.add(expectedColorText);

        confirmButton.setBounds(225, 400, 250, 50);
        confirmButton.setText("CONFIRM");
        this.add(confirmButton);

        leftChancesLabel.setBounds(5, 5, 100, 20);
        leftChancesLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        leftChancesLabel.setForeground(new Color(255, 255, 255));
        this.add(leftChancesLabel);

        expectedColorText.setBounds(400, 25, 200, 25);
        expectedColorText.setForeground(new Color(255, 255, 255));
        this.add(expectedColorText);

        confirmButton.setBounds(225, 400, 250, 50);
        confirmButton.setText("CONFIRM");
        this.add(confirmButton);

        leftChancesLabel.setBounds(5, 5, 100, 20);
        leftChancesLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
        leftChancesLabel.setForeground(new Color(255, 255, 255));
        this.add(leftChancesLabel);

    }

    public void setExpectedColor(Color color){
        expectedColor.setBackground(color);
        expectedColor.setForeground(new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue()));
    }

    public void resetPanel(){
        redSlider.setValue(127);
        redSlider.setEnabled(true);
        redColorPreview.setBackground(new Color(redSlider.getValue(), 0, 0));
        redColorPreview.setText("");
        greenSlider.setValue(127);
        greenSlider.setEnabled(true);
        greenColorPreview.setBackground(new Color(0, greenSlider.getValue(), 0));
        greenColorPreview.setText("");
        blueSlider.setValue(127);
        blueSlider.setEnabled(true);
        blueColorPreview.setBackground(new Color(0, 0, blueSlider.getValue()));
        blueColorPreview.setText("");
        currentColor.setBackground(new Color(255, 255, 255));
        leftChancesLabel.setText("Chances: 5");
    }


    public void setPreviewColor(String colorName, int singleColorValue){
        switch (colorName){
            case "red" -> redColorPreview.setBackground(new Color(singleColorValue, 0, 0));
            case "green" -> greenColorPreview.setBackground(new Color(0, singleColorValue, 0));
            case "blue" -> blueColorPreview.setBackground(new Color(0, 0, singleColorValue));
        }
    }

    public void setCurrentColorColor(Color color){
        currentColor.setBackground(color);
    }

    public void setLeftChancesLabel(int leftChances){
        leftChancesLabel.setText("Chances: " + leftChances);
    }

    public void setSliderAsFinal(String colorName, boolean isEnabled, boolean correctAnswer){
        String text = "      OK";
        if(!correctAnswer) text = "  WRONG";
        switch (colorName){
            case "red" -> {
                redSlider.setEnabled(isEnabled);
                redColorPreview.setText(text);
            }
            case "green" -> {
                greenSlider.setEnabled(isEnabled);
                greenColorPreview.setText(text);
            }
            case "blue" -> {
                blueSlider.setEnabled(isEnabled);
                blueColorPreview.setText(text);
            }
        }
    }

    @Override
    public void addButtonListener(ActionListener listener, String buttonId) {
        confirmButton.addActionListener(listener);
    }


    public void addSliderChangeListener(ChangeListener listener, String colorName){
        switch (colorName){
            case "red" -> redSlider.addChangeListener(listener);
            case "green" -> greenSlider.addChangeListener(listener);
            case "blue" -> blueSlider.addChangeListener(listener);
        }
    }

}