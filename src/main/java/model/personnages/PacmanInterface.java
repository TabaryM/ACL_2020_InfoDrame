package model.personnages;

import engine.controller.Cmd;

import java.awt.image.BufferedImage;

/**
 * @author Tabary
 */
public interface PacmanInterface extends PersonnageInterface {
    int getStreak();

    @Override
    void live();

    void attack();

    @Override
    void move();

    @Override
    void resetPosition();

    @Override
    void die();

    void decreaseVie();

    int getVie();

    void resetVie();

    void increaseVie();

    void setDir(Cmd commande);

    @Override
    BufferedImage getImage();

    boolean isAggressif();

    BufferedImage rotateImageByDegrees(BufferedImage img, double angle);

    void teleport();
}
