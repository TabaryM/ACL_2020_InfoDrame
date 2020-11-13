package model.personnages;

import engine.controller.Cmd;
import model.Monde;
import model.personnages.Personnage;
import model.plateau.Labyrinthe;
import model.plateau.Position;

public abstract class Fantome extends Personnage {

    protected Position pacmanPosition;

    public Fantome(Monde monde, Position position, Position pacmanPosition){
        super(monde, position);
        this.pacmanPosition = pacmanPosition;
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est vers la droite
    }

    public abstract void ia();
}
