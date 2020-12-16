package model.personnages;

import algorithmes.AEtoile;
import engine.controller.Cmd;
import model.MondeInterface;
import model.plateau.Case;
import model.plateau.Position;

import java.util.Collection;

import static engine.GameEngineGraphical.TIMESTEP;

public abstract class Fantome extends Personnage implements FantomeInterface {

    protected Position pacmanPosition;
    private double sleepingTime = 0.0;
    protected AEtoile aEtoile;

    /**
     * Constructeur de la superclasse des fantômes
     * @param monde le monde dans lequel le fantôme évolue
     * @param position la position du fantôme
     * @param pacmanPosition la position de Pacman
     */
    public Fantome(MondeInterface monde, Position position, Position pacmanPosition){
        super(monde, position);
        this.pacmanPosition = pacmanPosition;
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est vers la droite
    }

    /**
     * Fixe une durée de pause pour le fantôme
     * @param time le temps (en seconde) durant lequel le fantôme sera à l'arrêt
     */
    @Override
    public void setSleepingTime(double time){
        sleepingTime = time * 1000;
    }

    /**
     * Décrémente le temps durant lequel le fantôme dors (pour qu'il se réveille un jour).
     * Décrémente avec le temps de pas de jeu entre deux appels de la fonction nextStep()
     * Ce temps est accessible à GameEngineGraphical.
     */
    @Override
    public void decreaseSleepingTime() {
        if(isSleeping()){
            sleepingTime -= TIMESTEP;
        } else {
            sleepingTime = 0;
        }
    }

    /**
     * Méthode qui diminue le temps de sommeil des fantômes
     */
    @Override
    public void live() {
        decreaseSleepingTime();
    }

    /**
     * Méthode permettant de choisir si les fantômes bougent ou non, en fonction du temps de sommeil
     */
    @Override
    public void move() {
        anciennePosition.setCoord(position);
        ia();
        if(!isSleeping()){
            moveConcret();
        }
    }

    /**
     * Méthode permettant de faire bouger les fantômes
     */
    protected void moveConcret(){
        if(monde.getPacman().isAggressif()){
            aEtoile.resoudreLabyFuite();
        }
        else{
            aEtoile.resoudreLabyAttaque();
        }

        Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
        nextCase(aCase);
    }


    /**
     * Méthode permettant de choisir la direction d'un fantôme en fonction de la prochaine case
     * @param aCase la prochaine case empruntée par le fantôme
     */
    @Override
    public void nextCase(Case aCase) {
        if (aCase.getX() < position.getX()) {
            currentDirection = Cmd.LEFT;
            position.moveLeft();
        } else if (aCase.getX() > position.getX()) {
            currentDirection = Cmd.RIGHT;
            position.moveRight();
        } else if (aCase.getY() < position.getY()) {
            currentDirection = Cmd.UP;
            position.moveUp();
        } else if (aCase.getY() > position.getY()) {
            currentDirection = Cmd.DOWN;
            position.moveDown();
        }
    }

    /**
     * Méthode permettant l'attaque des différents personnages
     */
    @Override
    public void attack() {
        Collection<PersonnageInterface> personnageInterfaces = monde.getPersonnagesAt(position);
        personnageInterfaces.remove(this);
        for (PersonnageInterface p : personnageInterfaces){
            if(p.isPacman()) {
                if(!((PacmanInterface)p).isAggressif()) {
                    monde.kill(p);
                }
            }
        }

        // Test si le fantôme a échangé de position avec Pacman
        personnageInterfaces = monde.getPersonnages();
        personnageInterfaces.remove(this);
        for(PersonnageInterface p : personnageInterfaces){
            if(p.isPacman()) {
                if(!((PacmanInterface)p).isAggressif()) {
                    if (p.getPosition().equals(anciennePosition) && p.getAnciennePosition().equals(getPosition())) {
                        monde.kill(p);
                    }
                }
            }
        }
    }


    /**
     * Méthode permettant la mort des différents personnages
     */
    @Override
    public void die() {
        int score = getScore()*monde.getPacmanStreak();
        monde.increaseScore(score);
        resetPosition();
        setSleepingTime(6);
    }

    /**
     * Méthode permettant de reset la position des personnages à la mort de ceux-ci, ou lors des victoires et défaites
     */
    @Override
    public void resetPosition() {
        setPosition(monde.getPosSpawnFantome());
    }

    /**
     * Méthode permettant d'obtenir le score d'un fantôme
     * @return le score d'un fantôme
     */
    @Override
    public int getScore() {
        return 200;
    }

    /**
     * Méthode permettant de savoir si un fantôme est endormi ou non
     * @return l'état du fantôme
     */
    protected boolean isSleeping(){
        return sleepingTime > 0.000001;
    }
}
