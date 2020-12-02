package model.personnages;

import dataFactories.ImageFactory;
import model.Monde;
import model.personnages.Fantome;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePeureux extends Fantome {
    public FantomePeureux(Monde monde, Position position, Position pacmanPosition) {
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

            case UP: img = ImageFactory.getInstance().getFantomePeureuxHaut();
            break;

            case DOWN: img = ImageFactory.getInstance().getFantomePeureuxBas();
            break;

            case LEFT: img = ImageFactory.getInstance().getFantomePeureuxGauche();
            break;

            default: img = ImageFactory.getInstance().getFantomePeureuxDroite();
            break;
        }

        return img;
    }
}
