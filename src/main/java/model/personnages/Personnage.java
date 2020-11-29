package model.personnages;

import engine.controller.Cmd;
import model.Drawable;
import model.Monde;
import model.plateau.Position;

/**
 * @author Tabary
 */
public abstract class Personnage implements Drawable {
    protected final Monde monde;
    protected Cmd currentDirection;
    protected final Position position;
    protected final Position anciennePosition;

    /**
     * Initialise un personnage à la position donnée en paramètre
     * @param position la position initiale du personnage
     */
    public Personnage(Monde monde, Position position) {
        this.monde = monde;
        this.position = position;
        anciennePosition = new Position(0, 0);
    }

    public abstract void live();

    public abstract void move();

    public abstract void attack();

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

    public abstract void die();

    /**
     * Retourne le score procuré par le meurtre du personnage
     * @return 300 Si c'est un Fantôme.
     *         0 sinon.
     */
    public int getScore() {
        return 0;
    }

    public boolean isPacman(){
        return getScore() == 0;
    }
}
