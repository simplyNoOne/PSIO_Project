package gui.panels;

import interfaces.Interactible;
import main.ManagerHandler;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MenuPanel extends CustomPanel implements Interactible {

    NickField nick;
    JLabel title;

    public class FixedSizeDocument extends PlainDocument
    {
        private int max = 10;
        public FixedSizeDocument(int max) {
            this.max = max;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (getLength()+str.length()>max) {
                str = str.substring(0, max - getLength());
            }
            super.insertString(offs, str, a);
        }
    }

    public class NickField extends JTextField{
        boolean empty;
        NickField(){
            empty = true;
            this.setDocument(new FixedSizeDocument(11));
            this.addMouseListener(new NickListener());
            this.setText("Enter Name");
            this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            this.setForeground(Color.darkGray);
            this.setBounds(GUIManager.getWidth()/2 - 150, 280, 300, 40);
            this.setBackground(new Color(250, 250, 250));
            this.getCaret().setVisible(false);

        }

        public void checkEmpty(){
            this.getCaret().setVisible(true);
            if (empty){
                empty = false;
                setText("");
            }
        }

        public void setEmpty() {
            this.getCaret().setVisible(false);
            if (getText().equals("")) {
                empty = true;
                setText("Enter Name");
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
        this.setLayout(null);
        this.setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());

        JLabel bg = new JLabel(ManagerHandler.getResourceManager().getTexture("background"));
        bg.setSize(GUIManager.getWidth(), GUIManager.getHeight());


        title = new JLabel("THE LAS OF US",SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        title.setBounds((GUIManager.getWidth()-600)/2, 80, 600, 100);
        title.setForeground(Color.white);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 120));
        this.add(title);

        nick = new NickField();
        this.add(nick);

        buttons.put("start", new MenuButton("Start Game"));
        buttons.put("quit", new MenuButton("Quit Game"));
        buttons.put("scores", new MenuButton("See Scores"));

        buttons.get("start").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 - MenuButton.BUTTON_WIDTH -BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2 + 30, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        buttons.get("scores").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2 + 30, MenuButton. BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        buttons.get("quit").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 + MenuButton.BUTTON_WIDTH + BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2 + 30, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        this.add(buttons.get("start"));
        this.add(buttons.get("scores"));
        this.add(buttons.get("quit"));

        this.add(bg);       //bg must be added at the end, so that everything else displays above it

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);

    }

    public void showButtons(boolean show){
        buttons.forEach((key, value) -> value.setVisible(show));
        title.setVisible(show);
        nick.setVisible(show);
    }


}
