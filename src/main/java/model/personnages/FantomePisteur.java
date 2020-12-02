package model.personnages;

import algorithmes.AEtoilePisteur;
import dataFactories.ImageFactory;
import model.Monde;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePisteur extends Fantome {

    /**
     *
     * @param monde le monde dans lequel le fantôme évolue
     * @param position la position du fantôme
     * @param pacmanPosition la position de Pacman
     */
    public FantomePisteur(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    /**
     * Méthode permettant de lancer l'IA A*
     */
    @Override
    public void ia() {
        aEtoile = new AEtoilePisteur(monde, pacmanPosition, this.position);
    }

    @Override
    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePisteur();
    }
}
