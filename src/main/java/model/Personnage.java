package model;

import engine.controller.Cmd;
import engine.controller.Position;

/**
 * @author Tabary
 */
public abstract class Personnage {
    private Cmd currentDirection;
    // TODO : ajouter au diagramme de classe
    private final Position position;

    public Personnage(Position position) {
        this.position = position;
        this.currentDirection = Cmd.RIGHT; // L'orientation initiale de Pacman est vers la droite
    }

    public Personnage(){
        this(new Position());
    }

    public void setDir(Cmd commande) {
        currentDirection = commande;
    }

    public void move() {
        switch (currentDirection){
            case LEFT:
                position.moveLeft();
                break;
            case DOWN:
                position.moveDown();
                break;
            case UP:
                position.moveUp();
                break;
            case RIGHT:
                position.moveRight();
                break;
        }
    }
}
