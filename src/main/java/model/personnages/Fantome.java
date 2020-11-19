package model.personnages;

import engine.controller.Cmd;
import exception.PacmanException;
import model.Monde;
import model.personnages.Personnage;
import model.plateau.Labyrinthe;
import model.plateau.Position;

import java.util.Collection;
import java.util.List;

import static engine.GameEngineGraphical.TIMESTEP;

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
     * Décrémente avec le temps de pas de jeu entre deux appels de la fonction nextStep()
     * Ce temps est accessible à GameEngineGraphical.
     */
    public void decreaseSleepingTime() {
        if(isSleeping()){
            sleepingTime -= TIMESTEP;
        } else {
            sleepingTime = 0;
        }
    }

    @Override
    public void live() {
        decreaseSleepingTime();
    }

    @Override
    public void move() {
        if(!isSleeping()){
            moveConcret();
        }
    }

    protected abstract void moveConcret();

    @Override
    public void attack() {
        Collection<Personnage> personnages = monde.getPersonnagesAt(position);
        personnages.remove(this);
        for (Personnage p : personnages){
            if(p.isPacman()) {
                if(!((Pacman)p).isAggressif()) {
                    monde.kill(p);
                }
            }
        }
    }

    @Override
    public void die() {
        monde.increaseScore(getScore());
        resetPosition();
        setSleepingTime(6);
    }

    @Override
    public void resetPosition() {
        setPosition(monde.getPosSpawnFantome());
    }

    @Override
    protected int getScore() {
        return 300;
    }

    protected boolean isSleeping(){
        return sleepingTime > 0.000001;
    }
}
