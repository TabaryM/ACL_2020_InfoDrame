package model.personnages;

import model.plateau.Case;

/**
 * @author Tabary
 */
public interface FantomeInterface extends PersonnageInterface {
    /**
     * Méthode abstraite permettant de lancer l'ia des fantômes
     */
    void ia();

    void setSleepingTime(double time);

    void decreaseSleepingTime();

    @Override
    void live();

    @Override
    void move();

    void nextCase(Case aCase);

    @Override
    void attack();

    @Override
    void die();

    @Override
    void resetPosition();

    @Override
    int getScore();
}
