package model;

import java.util.List;

/**
 * @author Tabary
 */
public class Monde {
    // TODO : modifier diagramme de classe (déplacement des pieces dans le labyrinthe)
    private final Pacman pacman;
    private final Score score;
    private final Labyrinthe labyrinthe;

    Monde(){
        pacman = new Pacman();
        score = new Score();
        labyrinthe = new Labyrinthe();
    }

}
