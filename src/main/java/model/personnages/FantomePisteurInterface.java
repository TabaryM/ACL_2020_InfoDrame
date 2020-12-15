package model.personnages;

import java.awt.image.BufferedImage;

/**
 * @author Tabary
 */
public interface FantomePisteurInterface extends PersonnageInterface, FantomeInterface {
    @Override
    void ia();

    @Override
    BufferedImage getImage();
}
