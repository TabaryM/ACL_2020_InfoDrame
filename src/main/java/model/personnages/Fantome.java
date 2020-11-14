package model.personnages;

import engine.controller.Cmd;
import exception.PacmanException;
import model.Monde;
import model.personnages.Personnage;
import model.plateau.Labyrinthe;
import model.plateau.Position;

import java.util.List;

public abstract class Fantome extends Personnage {

    protected Position pacmanPosition;
    private double sleepingTime = 0.0;

    public Fantome(Monde monde, Position position, Position pacmanPosition){
        super(monde, position);
        this.pacmanPosition = pacmanPosition;
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est vers la droite
    }

    public abstract void ia();

    /**
     * Fixe une durée de pause pour le fantôme
     * @param time le temps (en seconde) durant lequel le fantôme sera à l'arrêt
     */
    public void setSleepingTime(double time){
        sleepingTime = time * 1000;
    }

    /**
     * Décrémente le temps durant lequel le fantôme dors (pour qu'il se réveille un jour).
     * Décrémente avec le temps de pas de jeu entre deux appels de la fonction nextStep()/
     * Ce temps est accessible à GameEngineGraphical.
     * @param time double positif qui sera soustrait à sleepingTime.
     * @throws PacmanException si le temps de jeu est négatif ou supérieur à sleepingTime
     */
    public void decreaseSleepingTime(double time) throws PacmanException {
        if(time < 0 || sleepingTime - time < 0){
            throw new PacmanException("Pas de temps de jeu incorrect");
        }
        sleepingTime -= time;
    }

    @Override
    public void resetPosition() {
        setPosition(monde.getPosSpawnFantome());
        setSleepingTime(15);
    }
}
