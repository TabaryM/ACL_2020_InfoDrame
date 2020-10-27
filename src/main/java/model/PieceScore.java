package model;

/**
 * @author Tabary
 */
public class PieceScore extends Piece {

    /**
     * Constructeur d'une pièce augmentant le score. Il définit la pièce en fonction de sa position.
     * @param x la position sur l'axe x de la pièce.
     * @param y la position sur l'axe y de la pièce.
     */
    public PieceScore(int x, int y) {
        super(x, y);
    }

    /**
     * Méthode abstraite permettant de récupéré la valeur du score de la pièce de score.
     * @return la valeur de score de la pièce de score.
     */
    @Override
    public int getScore() {
        return 10;
    }
}
