package model.personnages;

import engine.controller.Cmd;
import model.Drawable;
import model.MondeInterface;
import model.plateau.Position;

/**
 * @author Tabary
 */
public interface PersonnageInterface extends Drawable {
    void live();

    void move();

    void attack();

    void setPosition(int x, int y);

    void setPosition(Position position);

    Position getPosition();

    Cmd getCurrentDirection();

    // TODO : tester mock
    void resetPosition();

    void die();

    int getScore();

    boolean isPacman();

    MondeInterface getMonde();

    Position getAnciennePosition();
}
