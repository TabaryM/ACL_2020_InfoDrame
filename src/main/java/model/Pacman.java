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

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

}
