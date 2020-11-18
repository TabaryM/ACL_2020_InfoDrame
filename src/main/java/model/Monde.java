package model;

import engine.controller.Cmd;
import exception.PacmanException;
import model.personnages.Fantome;
import model.personnages.FantomePisteur;
import model.plateau.Case;
import model.plateau.Position;
import model.personnages.Pacman;
import model.personnages.Personnage;
import model.plateau.Labyrinthe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static engine.GameEngineGraphical.TIMESTEP;

/**
 * @author Tabary
 */
public class Monde {
    private final Pacman pacman;
    private final Fantome fantomePisteur;
    private int score;
    private final Labyrinthe labyrinthe;
    private final Collection<Personnage> personnages;
    private final PropertyChangeSupport pcs;

    /**
     * Initialisation du monde à partir d'un labyrinthe
     * @param labyrinthe le plateau de jeu initial
     */
    Monde(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
        pacman = new Pacman(this, labyrinthe.getPositionInitialPacman());
        fantomePisteur = new FantomePisteur(this, getPosSpawnFantome(), pacman.getPosition());
        score = 0;
        personnages = new ArrayList<>();
        personnages.add(pacman);
        personnages.add(fantomePisteur);
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

    public Case[] getVoisins(Position position) {
        Case[] res = new Case[4];

            res[0] = labyrinthe.getCasePlateau(position.getX()-1, position.getY());     // à gauche

            res[2] = labyrinthe.getCasePlateau(position.getX()+1, position.getY());     // à droite

            res[1] = labyrinthe.getCasePlateau(position.getX(), position.getY()-1);     // en haut

            res[3] = labyrinthe.getCasePlateau(position.getX(), position.getY()+1);     // en bas

        return res;
    }

    /**
     * Met à jour la direction de Pacman
     * @param commande la commande saisie par l'utilisateur
     */
    public void setJoueurDir(Cmd commande) {
        if(commande.equals(Cmd.LEFT) || commande.equals(Cmd.UP) || commande.equals(Cmd.RIGHT) || commande.equals(Cmd.DOWN)){
            pacman.setDir(commande);
        }
    }

    /**
     * Calcule la prochaine étape du jeu
     */
    public void nextStep(){
        fantomePisteur.ia();
        for (Personnage p : personnages){
            p.live();
            //System.out.println(p.getPosition() + " status : "+p.getCurrentDirection());
        }
        //System.out.println(labyrinthe);
        //System.out.println(score);
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

    public int getCote() {
        return labyrinthe.getCote();
    }

    public Case getCaseAt(Position position){
        return labyrinthe.getCasePlateau(position);
    }

    /**
     * Regarde à la position passée en paramètre.
     * S'il y a une pièce, la retire du labyrinthe
     * @param position la position de la pièce ramassée
     * @return Si elle existe, la pièce.
     *         Sinon, null.
     */
    public Piece grabPieceAt(Position position){
        Piece piece = labyrinthe.getPiece(position);
        if(piece != null){
            labyrinthe.deletePiece(position);
        }
        return piece;
    }

    /**
     * Méthode listant les personnages à une position
     * @param position la position où on regarde
     * @return la liste des personnages à la position demandée
     */
    public Collection<Personnage> getPersonnagesAt(Position position){
        Collection<Personnage> res = new ArrayList<>();
        for (Personnage p : personnages){
            if (p.getPosition().equals(position)){
                res.add(p);
            }
        }
        return res;
    }

    /**
     * Tue un personnage et le remet à sa position de départ
     * @param personnage le personnage qui est mort
     */
    public void kill(Personnage personnage) {
        System.out.println("AAAAAAAAAAAAAHHHHHHHHH " + personnage.getClass().getSimpleName() + " EST MORT ! ");
        personnage.resetPosition();
    }

    /**
     * Retourne une position de spawn pour un fantôme aléatoire parmis toutes disponible
     * @return Position une position de spawn de fantôme
     */
    public Position getPosSpawnFantome() throws PacmanException {
        List<Position> positions = labyrinthe.getPosInitFantome();
        Random random = new Random(System.currentTimeMillis());
        return positions.get(random.nextInt(positions.size()));
    }

    public Position getPosInitPacman() {
        return labyrinthe.getPositionInitialPacman();
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Collection<Personnage> getPersonnages() {
        return personnages;
    }
}
