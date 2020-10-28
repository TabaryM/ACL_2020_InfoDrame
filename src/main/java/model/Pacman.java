package model;

/**
 * @author Tabary
 */
public class Pacman extends Personnage{
    private int vie = 3 ;




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

}
