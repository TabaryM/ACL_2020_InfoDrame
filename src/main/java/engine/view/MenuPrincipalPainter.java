package engine.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Tabary
 */
public class MenuPrincipalPainter implements MenuUI{

    @Override
    public void display() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Play");
        JTextArea title = new JTextArea("PacMan");

        panel.add(title);
        panel.add(button);
        Dimension dimension = new Dimension();
        dimension.setSize(300, 400);

        JFrame f = new JFrame();
        f.setTitle("PacMan");
        f.setMinimumSize(dimension);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(panel);
        f.pack();
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
    }
}
