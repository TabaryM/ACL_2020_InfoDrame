package engine.controller;

/**
 * @author Tabary
 */
public class MenuPauseController implements MenuController {

    private boolean play;

    /**
     * Constructeur du controller du menu pause, il permet de définir l'état du jeu sur play
     */
    public MenuPauseController(){
        play = true;
    }

    /**
     * Méthode permettant de savoir si le jeu est en pause ou non
     * @return l'état du jeu
     */
    @Override
    public boolean play() {
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
