package model.personnages;

import algorithmes.AEtoilePiegeur;
import dataFactories.ImageFactory;
import model.Monde;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePiegeur extends Fantome {

    private final String direction;

    /**
     *
     * @param monde le monde dans lequel le fantôme évolue
     * @param position la position du fantôme
     * @param pacmanPosition la position de Pacman
     * @param direction
     */
    public FantomePiegeur(Monde monde, Position position, Position pacmanPosition, String direction) {
        super(monde, position, pacmanPosition);
        this.direction = direction;
    }

    /**
     * Méthode permettant de lancer l'IA A*
     */
    @Override
    public void ia() {
        aEtoile = new AEtoilePiegeur(monde, pacmanPosition, this.position, direction);
    }

    public String getDirection() {
        return direction;
    }

    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePiegeur();
    }
}
