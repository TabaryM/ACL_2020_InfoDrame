package model.personnages;

import java.awt.image.BufferedImage;

/**
 * @author Tabary
 */
public interface FantomePeureuxInterface extends PersonnageInterface, FantomeInterface {
    @Override
    void ia();

    void moveConcret();

    @Override
    BufferedImage getImage();
}
