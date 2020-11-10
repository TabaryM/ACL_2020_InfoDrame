package model;

import engine.controller.Cmd;
import interfaceModel.Monde;
import model.plateau.Case;
import model.plateau.Position;
import model.personnages.Pacman;
import model.personnages.Personnage;
import model.plateau.Labyrinthe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tabary
 */
public class MondePacman implements Monde {
    private final Pacman pacman;
    private int score;
    private final Labyrinthe labyrinthe;
    private final List<Personnage> personnages;
    private final PropertyChangeSupport pcs;

    /**
     * Initialisation du monde à partir d'un labyrinthe
     * @param labyrinthe le plateau de jeu initial
     */
    MondePacman(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
        pacman = new Pacman(this, labyrinthe.getPositionInitialPacman());
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

    @Override
    public Case[] getVoisins(Position position) {
        Case[] res = new Case[4];
        res[0] = labyrinthe.getCasePlateau(position.getX()-1, position.getY());     // à gauche
        res[1] = labyrinthe.getCasePlateau(position.getX(), position.getY()-1);     // en haut
        res[2] = labyrinthe.getCasePlateau(position.getX()+1, position.getY());     // à droite
        res[3] = labyrinthe.getCasePlateau(position.getX(), position.getY()+1);     // en bas
        return res;
    }

    /**
     * Met à jour la direction de Pacman
     * @param commande la commande saisie par l'utilisateur
     */
    @Override
    public void setJoueurDir(Cmd commande) {
        if(commande.equals(Cmd.LEFT) || commande.equals(Cmd.UP) || commande.equals(Cmd.RIGHT) || commande.equals(Cmd.DOWN)){
            pacman.setDir(commande);
        }
    }

    /**
     * Calcule la prochaine étape du jeu
     */
    public void nextStep(){
        for (Personnage p : personnages){
            p.move();
            if (labyrinthe.getPiece(p.getPosition()) != null){
                increaseScore(labyrinthe.getPiece(p.getPosition()).getScore());
                System.out.println(labyrinthe.getPiece(p.getPosition()).getScore());
                labyrinthe.deletePiece(p.getPosition());
            }
            System.out.println(p.getPosition() + " status : "+p.getCurrentDirection());
        }
        //System.out.println(labyrinthe);
        System.out.println(score);
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
