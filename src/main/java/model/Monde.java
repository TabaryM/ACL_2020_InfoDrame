package model;

import engine.controller.Cmd;
import engine.controller.Position;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tabary
 */
public class Monde  {
    // TODO : modifier diagramme de classe (déplacement des pieces dans le labyrinthe)
    private final Pacman pacman;
    private int score;
    private final Labyrinthe labyrinthe;
    private final List<Personnage> personnages;
    private final PropertyChangeSupport pcs;

    /**
     * Initialisation du monde à partir d'un labyrinthe
     * @param labyrinthe le plateau de jeu initial
     */
    Monde(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
        pacman = new Pacman();
        pacman.setPosition(labyrinthe.getPositionInitialPacman());
        score = 0;
        personnages = new ArrayList<Personnage>();
        personnages.add(pacman);
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * Ajoute un PropertyChangeListener au PropertyChangeSupport
     * @param l de type PropertyChangeListener
     */
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("score", l);
        pcs.addPropertyChangeListener("vie", l);
    }

    /**
     * Met à jour la direction de Pacman
     * @param commande la commande saisie par l'utilisateur
     */
    public void setPacmanDir(Cmd commande) {
        if(commande.equals(Cmd.LEFT) || commande.equals(Cmd.UP) || commande.equals(Cmd.RIGHT) || commande.equals(Cmd.DOWN)){
            pacman.setDir(commande);
        }
    }

    /**
     * Calcule la prochaine étape du jeu
     */
    public void nextStep(){
        for (Personnage p : personnages){
            if(personnagePeutAvancer(p)){
                p.move();
                if (labyrinthe.getPiece(p.getPosition().getX(), p.getPosition().getY()) != null){
                    increaseScore(labyrinthe.getPiece(p.getPosition().getX(), p.getPosition().getY()).getScore());
                    System.out.println(labyrinthe.getPiece(p.getPosition().getX(), p.getPosition().getY()).getScore());
                    labyrinthe.deletePiece(p.getPosition().getX(), p.getPosition().getY());
                }
            }
            System.out.println(p.getPosition() + " status : "+p.getCurrentDirection());
        }
        //System.out.println(labyrinthe);
        System.out.println(score);
    }

    /**
     * Calcul si un personnage peut avancer (s'il n'avance pas vers un mur)
     * @param p Personnage qui peut (ou non) avancer
     * @return boolean true si le personnage peut avancer, false sinon
     */
    public boolean personnagePeutAvancer(Personnage p){
        boolean tmp = true;
        Position pos = p.getPosition();
        switch (p.getCurrentDirection()){
            case LEFT:
                tmp = labyrinthe.getCasePlateau(pos.getX()-1, pos.getY()) != '1';
                break;
            case DOWN:
                tmp = labyrinthe.getCasePlateau(pos.getX(), pos.getY()+1) != '1';
                break;
            case UP:
                tmp = labyrinthe.getCasePlateau(pos.getX(), pos.getY()-1) != '1';
                break;
            case RIGHT:
                tmp = labyrinthe.getCasePlateau(pos.getX()+1, pos.getY()) != '1';
                break;
        }
        return tmp;
    }

    /**
     * Méthode permettant d'augmenter le score du joueur en fonction des pièces récupérées.
     * @param i les points ajoutés au score lorsque le joueur récupère une pièce.
     */
    public void increaseScore(int i) {
        int scoreOld = this.score;
        score += i;
        pcs.firePropertyChange("score", scoreOld, this.score);
    }

    /**
     * Méthode qui enlève une vie au joueur (pacman)
     */
    public void decreasedVie() {
        int oldVie = pacman.getVie();
        pacman.decreasedVie();
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }
}
