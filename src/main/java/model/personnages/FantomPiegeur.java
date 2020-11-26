package model.personnages;

import dataFactories.ImageFactory;
import model.Monde;
import model.personnages.Fantome;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomPiegeur extends Fantome {

    public FantomPiegeur(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {
    }

    @Override
    public void moveConcret() {
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
