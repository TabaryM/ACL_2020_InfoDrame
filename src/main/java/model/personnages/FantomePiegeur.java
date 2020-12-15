package model.personnages;

import algorithmes.AEtoilePiegeur;
import dataFactories.ImageFactory;
import model.MondeInterface;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePiegeur extends Fantome implements FantomePiegeurInterface {

    private final String direction;

    /**
     *
     * @param monde le monde dans lequel le fantôme évolue
     * @param position la position du fantôme
     * @param pacmanPosition la position de Pacman
     * @param direction
     */
    public FantomePiegeur(MondeInterface monde, Position position, Position pacmanPosition, String direction) {
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

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage img;

        switch (this.currentDirection) {

            case UP: img = ImageFactory.getInstance().getFantomePiegeurHaut();
                break;

            case DOWN: img = ImageFactory.getInstance().getFantomePiegeurBas();
                break;

            case LEFT: img = ImageFactory.getInstance().getFantomePiegeurGauche();
                break;

            default: img = ImageFactory.getInstance().getFantomePiegeurDroite();
                break;
        }

        return img;
    }
}
