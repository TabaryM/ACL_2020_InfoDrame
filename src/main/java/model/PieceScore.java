package model;

import dataFactories.ImageFactory;
import model.plateau.Position;

import java.awt.image.BufferedImage;

/**
 * @author Tabary
 */
public class PieceScore extends Piece {

    /**
     * Constructeur d'une pièce augmentant le score. Il définit la pièce en fonction de sa position.
     * @param position la position de la pièce dans le monde.
     */
    public PieceScore(Position position) {
        super(position);
    }

    /**
     * Méthode abstraite permettant de récupéré la valeur du score de la pièce de score.
     * @return la valeur de score de la pièce de score.
     */
    @Override
    public int getScore() {
        return 10;
    }

    @Override
    public BufferedImage getImage() {
        return ImageFactory.getInstance().getPieceScore();
    }
}
