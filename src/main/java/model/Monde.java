package model;

import engine.controller.Cmd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tabary
 */
public class Monde {
    // TODO : modifier diagramme de classe (déplacement des pieces dans le labyrinthe)
    private final Pacman pacman;
    private final Score score;
    private final Labyrinthe labyrinthe;
    private final List<Personnage> personnages;

    // TODO : parametre via lecture de fichier
    Monde(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
        pacman = new Pacman();
        score = new Score();
        personnages = new ArrayList<Personnage>();
        personnages.add(pacman);
    }

    public void setPacmanDir(Cmd commande) {
        if(!commande.equals(Cmd.IDLE)){
            pacman.setDir(commande);
        }
    }

    public void nextStep(){
        for (Personnage p : personnages){
            p.move();
        }
    }
}
