package model;

import model.plateau.Position;

/**
 * @author Tabary
 */
public class PieceAttaque extends Piece{

    /**
     * Constructeur d'une pièce permettant l'attaque. Il définit la pièce en fonction de sa position.
     * @param position la position de la pièce dans le monde.
     */
    public PieceAttaque(Position position) {
        super(position);
    }

    /**
     * Méthode abstraite permettant de récupéré la valeur du score de la pièce d'attaque.
     * @return la valeur de score de la pièce.
     */
    @Override
    public int getScore() {
        return 50;
    }
}
