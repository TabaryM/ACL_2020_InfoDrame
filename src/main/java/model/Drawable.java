package model;

import java.awt.image.BufferedImage;

public interface Drawable {

    /**
     * Récupère l'image correspondant à l'objet
     * @return BufferedImage
     */
    BufferedImage getImage();
}
