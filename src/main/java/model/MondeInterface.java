package model;

import engine.controller.Cmd;
import exception.PacmanException;
import model.personnages.PacmanInterface;
import model.personnages.PersonnageInterface;
import model.plateau.Case;
import model.plateau.LabyrintheInterface;
import model.plateau.Position;

import java.beans.PropertyChangeListener;
import java.util.Collection;

/**
 * @author Tabary
 */
public interface MondeInterface {
    void setLabyrinthe(LabyrintheInterface labyrintheInterface);

    void addObserver(PropertyChangeListener l);

    Case[] getVoisins(Position position);

    void setJoueurDir(Cmd commande);

    void nextStep();

    void increaseScore(int i);

    void decreasedVie();

    // TODO : tester
    void resetAllPosition();

    void increaseVie();

    void resetPacmanVie();

    int getLargeur();

    int getHauteur();

    Case getCaseAt(Position position);

    Piece grabPieceAt(Position position);

    Collection<PersonnageInterface> getPersonnagesAt(Position position);

    void kill(PersonnageInterface personnageInterface);

    void setPlay(boolean play);

    Position getPosSpawnFantome() throws PacmanException;

    Position getPosInitPacman();

    LabyrintheInterface getLabyrinthe();

    PacmanInterface getPacman();

    int getPacmanStreak();

    Collection<PersonnageInterface> getPersonnages();

    boolean pacmanWon();

    boolean pacmanLost();

    void resetScore();

    int getScore();
}
