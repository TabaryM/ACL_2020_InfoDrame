package model;

import engine.controller.Position;

/**
 * @author Tabary
 */
public abstract class Piece {

    protected int x;
    protected int y;

    /**
     * Constructeur d'une pièce. Il définit la pièce en fonction de sa position.
     * @param x la position sur l'axe x d'une pièce.
     * @param y la position sur l'axe y d'une pièce.
     */
    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Méthode permettant de retrouver la position sur l'axe x d'une pièce.
     * @return la position d'une pièce sur l'axe x.
     */
    public int getX() {
        return x;
    }

    /**
     * Méthode permettant de retrouver la position sur l'axe y d'une pièce.
     * @return la position d'une pièce sur l'axe y.
     */
    public int getY() {
        return y;
    }

    /**
     * Méthode abstraite permettant de récupéré la valeur du score d'un epièce.
     * @return la valeur de score d'une pièce.
     */
    public abstract int getScore();
}
