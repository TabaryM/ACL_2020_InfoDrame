package interfaceModel;

import engine.controller.Cmd;
import model.plateau.Case;
import model.plateau.Position;

import java.beans.PropertyChangeListener;

/**
 * @author Tabary
 */
public interface Monde {
    void setJoueurDir(Cmd commande);
    void nextStep();
    void addObserver(PropertyChangeListener l);

    Case[] getVoisins(Position position);
}
