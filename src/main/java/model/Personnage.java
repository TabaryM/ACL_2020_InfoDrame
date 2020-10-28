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

    /**
     * Initialise un personnage à la position donnée en paramètre
     * @param position la position initiale du personnage
     */
    public Personnage(Position position) {
        this.position = position;
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est vers la droite
    }

    /**
     * Initialise un personnage à une position par défaut (0, 0)
     */
    public Personnage(){
        this(new Position());
    }

    // TODO : faire ça uniquement pour Pacman
    public void setDir(Cmd commande) {
        currentDirection = commande;
    }

    // TODO : abstract pour que les fantomes se déplacent par eux mêmes
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

    /**
     * Fixe la position du personnage
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     */
    public void setPosition(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Fixe la position du personnage
     * @param position la nouvelle position du personnage
     */
    public void setPosition(Position position){
        setPosition(position.getX(), position.getY());
    }

    /**
     * Retourne la position du personnage
     * @return position en abscisse et en ordonnée
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retourne la direction actuelle du Pacman
     * @return currentDirection
     */
    public Cmd getCurrentDirection() {
        return currentDirection;
    }
}
