package model.personnages;

import engine.controller.Cmd;
import model.MondeInterface;
import model.plateau.Position;

/**
 * @author Tabary
 */
public abstract class Personnage implements PersonnageInterface {
    protected final MondeInterface monde;
    protected Cmd currentDirection;
    protected final Position position;
    protected final Position anciennePosition;

    /**
     * Initialise un personnage à la position donnée en paramètre
     * @param position la position initiale du personnage
     */
    public Personnage(MondeInterface monde, Position position) {
        this.monde = monde;
        this.position = position;
        anciennePosition = new Position(0, 0);
    }

    /**
     * Fixe la position du personnage
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     */
    @Override
    public void setPosition(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Fixe la position du personnage
     * @param position la nouvelle position du personnage
     */
    @Override
    public void setPosition(Position position){
        setPosition(position.getX(), position.getY());
    }

    /**
     * Retourne la position du personnage
     * @return position en abscisse et en ordonnée
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Retourne la direction actuelle du Pacman
     * @return currentDirection
     */
    @Override
    public Cmd getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Retourne le score procuré par le meurtre du personnage
     * @return 200 Si c'est un Fantôme.
     *         0 sinon.
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     * Méthode permettant de savoir si le personnage observé est Pacman ou non, grâce au score lors de la mort.
     * Pacman n'augmentant pas le score inexistant des fantômes.
     * @return si le personnage est PAcman ou non.
     */
    @Override
    public boolean isPacman(){
        return getScore() == 0;
    }

    @Override
    public MondeInterface getMonde() {
        return monde;
    }

    @Override
    public Position getAnciennePosition() {
        return anciennePosition;
    }
}
