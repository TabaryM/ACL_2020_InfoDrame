package model;

import engine.controller.Cmd;

/**
 * @author Tabary
 */
public class Pacman extends Personnage{
    private int vie = 3 ;

    public Pacman(){
        super();
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est vers la droite
    }

    @Override
    public void move() {
        switch (currentDirection){
            case LEFT:
                position.moveLeft();
                break;
            case DOWN:
                position.moveDown();
                break;
            case UP:
                position.moveUp();
                break;
            case RIGHT:
                position.moveRight();
                break;
        }
    }

    /**
     * Methode qui decremnte de 1 la vie de pacman
     */
    public void decreasedVie() {
        this.vie--;
    }

    /**
     * Retourne le nombre de vie de pacman
     * @return int vie
     */
    public int getVie() {
        return vie;
    }

    /**
     * Fixe le nombre de vie à Pacman
     * @param vie int le nombre de vie de Pacman
     */
    public void setVie(int vie) {
        this.vie = vie;
    }

    // TODO : faire ça uniquement pour Pacman
    public void setDir(Cmd commande) {
        currentDirection = commande;
    }

}
