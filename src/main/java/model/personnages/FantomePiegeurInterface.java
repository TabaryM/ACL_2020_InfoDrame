package model.personnages;

import java.awt.image.BufferedImage;

/**
 * @author Tabary
 */
public interface FantomePiegeurInterface extends PersonnageInterface, FantomeInterface {
    @Override
    void ia();

    String getDirection();

    BufferedImage getImage();
}
