package engine.view;

import engine.controller.MenuPauseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tabary
 */
public class MenuPausePainter implements MenuUI, ActionListener {

    private MenuPauseController menuPauseController;
    private JFrame f;

    public MenuPausePainter(MenuPauseController menuPauseController){
        this.menuPauseController = menuPauseController;
        f = new JFrame();
    }

    @Override
    public void create() {

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0,0));
        JButton button = new JButton("Resume");

        button.addActionListener(this);
        panel.add(button);
        Dimension dimension = new Dimension();
        dimension.setSize(300, 400);

        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        f = new JFrame();
        f.setTitle("PacMan");
        f.setMinimumSize(dimension);
        f.setLocation(new Point((screen.width-dimension.width)/2, (screen.height-dimension.height)/2));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setUndecorated(true);
        f.setBackground(new Color(0,0,0,0));
        f.setContentPane(panel);
        f.pack();

    }

    @Override
    public void display() {
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
    }

    @Override
    public void erase() {
        f.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuPauseController.changePlay();
    }
}
