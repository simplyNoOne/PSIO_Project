package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MenuPanel extends CustomPanel implements Interactible {

    NickField nick;
    JLabel title;

    public class NickField extends JTextField{
        boolean empty;
        NickField(){
            empty = true;
            this.addMouseListener(new NickListener());
            this.setText("Enter Nickname");
            this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            this.setForeground(Color.darkGray);
            this.setBounds(GUIManager.getWidth()/2 - 150, 280, 300, 40);
            this.setBackground(new Color(250, 250, 250));
        }

        public void checkEmpty(){
            if (empty){
                empty = false;
                setText("");
            }
        }

        public void setEmpty() {
            if (getText().equals("")) {
                empty = true;
                setText("Enter nickname");
            }
        }
    }

    public NickField getNickField() {
        return nick;
    }

    class NickListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            ((NickField)e.getSource()).checkEmpty();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((NickField)e.getSource()).setEmpty();
        }
    }


    static class MenuButton extends JButton{
        static final int BUTTON_WIDTH = 200;
        static final int BUTTON_HEIGHT = 60;

        MenuButton(){
            super();
            this.setFocusPainted(false);
        }
        MenuButton(String text){
            this();
            this.setText(text);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
            this.setFocusPainted(false);

        }
    }

    static final int BUTTON_SPACE = 30;


    Map<String, MenuButton> buttons = new HashMap<>();

    public MenuPanel(){
        super();

        nick = new NickField();
        this.add(nick);
        JLabel bg = new JLabel(ResourceManager.getTexture("background"));
        bg.setSize(GUIManager.getWidth(), GUIManager.getHeight());

        title = new JLabel("THE LAS OF US",SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        title.setBounds((GUIManager.getWidth()-600)/2, 80, 600, 100);
        title.setForeground(Color.white);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 120));

        this.add(title);


        buttons.put("start", new MenuButton("Start Game"));
        buttons.put("quit", new MenuButton("Quit Game"));
        buttons.put("scores", new MenuButton("See Scores"));

        this.setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());
        //this.setBackground(Color.blue);
        this.setLayout(null);
        buttons.get("start").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 - MenuButton.BUTTON_WIDTH -BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2 + 30, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        buttons.get("scores").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2 + 30, MenuButton. BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        buttons.get("quit").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 + MenuButton.BUTTON_WIDTH + BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2 + 30, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        this.add(buttons.get("start"));
        this.add(buttons.get("scores"));
        this.add(buttons.get("quit"));
        this.add(bg);


    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);

    }

    public void showButtons(boolean show){
        buttons.forEach((key, value) -> value.setVisible(show));
        title.setVisible(show);
    }


}
