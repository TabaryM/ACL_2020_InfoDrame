package model.personnages;

import engine.controller.Cmd;
import model.Monde;
import model.plateau.Position;

/**
 * @author Tabary
 */
public abstract class Personnage {
    protected final Monde monde;
    protected Cmd currentDirection;
    protected final Position position;

    /**
     * Initialise un personnage à la position donnée en paramètre
     * @param position la position initiale du personnage
     */
    public Personnage(Monde monde, Position position) {
        this.monde = monde;
        this.position = position;
    }

    public void live(){
        move();
    }

    public abstract void move();

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

    public abstract void resetPosition();
}
