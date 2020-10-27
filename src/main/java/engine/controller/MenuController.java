package engine.controller;

/**
 * @author Roberge-Mentec Corentin
 */

public interface MenuController {

    /**
     * Méthode permettant de savoir si le jeu est en pause ou non
     * @return l'état du jeu
     */
    boolean play();

    /**
     * Méthode permettant de changer l'état du jeu
     */
    void changePlay();
}
