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



    /**
     * Constructeur de l'afficheur du menu principal. Il construit la nouvelle fenêtre qui va acceuillir le menu
     * principal et défini son controller.
     * @param menuPrincipalController le controller de l'afficheur du menu principal.
     */
    public MenuPrincipalPainter(MenuPrincipalController menuPrincipalController){
        this.menuPrincipalController = menuPrincipalController;
    }

    /**
     * Crée les composants de la fenêtre de l'afficheur.
     */
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

    /**
     * Affiche la fenêtre du menu principal.
     */
    @Override
    public void display(){
        f.setVisible(true);
        f.getContentPane().setFocusable(true);
        f.getContentPane().requestFocus();
    }

    /**
     * Efface la fenêtre du menu principal.
     */
    @Override
    public void erase() {
        f.dispose();
    }

    /**
     * Permet de notifier le controller d'un événement ayant eu lieu dans la fenêtre.
     * @param e l'événement ayant eu lieu dans la fenêtre de l'afficheur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        menuPrincipalController.changePlay();
    }
}
