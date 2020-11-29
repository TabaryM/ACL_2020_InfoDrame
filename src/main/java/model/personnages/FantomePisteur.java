package model.personnages;

import algorithmes.AEtoilePisteur;
import dataFactories.ImageFactory;
import model.Monde;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePisteur extends Fantome {

    public FantomePisteur(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {
        aEtoile = new AEtoilePisteur(monde, pacmanPosition, this.position);
    }

    @Override
    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePisteur();
    }
}
