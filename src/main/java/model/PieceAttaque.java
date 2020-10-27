package model;

/**
 * @author Tabary
 */
public class PieceAttaque extends Piece{

    /**
     * Constructeur d'une pièce permettant l'attaque. Il définit la pièce en fonction de sa position.
     * @param x la position sur l'axe x de la pièce.
     * @param y la position sur l'axe y de la pièce.
     */
    public PieceAttaque(int x, int y) {
        super(x, y);
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
