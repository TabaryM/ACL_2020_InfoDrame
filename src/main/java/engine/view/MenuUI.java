package engine.view;

/**
 * @author Roberge-Mentec Corentin
 */

public interface MenuUI {

    /**
     * Crée les composants de la fenêtre de l'afficheur.
     */
    void create();

    /**
     * Affiche la fenêtre du menu principal.
     */
    void display();

    /**
     * Efface la fenêtre du menu.
     */
    void erase();
}
