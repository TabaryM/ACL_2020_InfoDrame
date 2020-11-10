package model;

import model.plateau.Position;

/**
 * @author Tabary
 */
public abstract class Piece {

    private final Position position;

    /**
     * Constructeur d'une pièce. Il définit la pièce en fonction de sa position.
     * @param position la position de la pièce dans le monde.
     */
    public Piece(Position position){
        this.position = position;
    }

    /**
     * Méthode permettant de retrouver la position sur l'axe x d'une pièce.
     * @return la position d'une pièce sur l'axe x.
     */
    public int getX() {
        return position.getX();
    }

    /**
     * Méthode permettant de retrouver la position sur l'axe y d'une pièce.
     * @return la position d'une pièce sur l'axe y.
     */
    public int getY() {
        return position.getY();
    }

    /**
     * Méthode abstraite permettant de récupéré la valeur du score d'un epièce.
     * @return la valeur de score d'une pièce.
     */
    public abstract int getScore();

    @Override
    public String toString(){
        return "o ";
    }
}
