package engine.controller;

import engine.view.GraphicalInterface;

/**
 * @author Tabary
 */
public class MenuPrincipalController implements MenuController {

    private boolean play;

    /**
     * Constructeur du menu principal du jeu, il permet de définir l'état du jeu sur pause.
     */
    public MenuPrincipalController(){
        play = false;
    }

    /**
     * Méthode permettant de savoir si le jeu est en pause ou non
     * @return l'état du jeu
     */
    @Override
    public boolean play(){
        return play;
    }

    /**
     * Méthode permettant de changer l'état du jeu
     */
    @Override
    public void changePlay(){
        play = !play;
    }
}
