package engine.view;

import engine.controller.MenuPrincipalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tabary
 */
public class MenuPrincipalPainter implements MenuUI, ActionListener {

    private MenuPrincipalController menuPrincipalController;
    private JFrame f;

    public MenuPrincipalPainter(MenuPrincipalController menuPrincipalController){
        this.menuPrincipalController = menuPrincipalController;
    }

    @Override
    public void create() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Play");
        JTextArea title = new JTextArea("PacMan");

        button.addActionListener(this);
        panel.add(title);
        panel.add(button);
        Dimension dimension = new Dimension();
        dimension.setSize(300, 400);

        f = new JFrame();
        f.setTitle("PacMan");
        f.setMinimumSize(dimension);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(panel);
        f.pack();
    }

    @Override
    public void erase() {
        f.dispose();
    }

    public void display(){
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuPrincipalController.changePlay();
    }
}
