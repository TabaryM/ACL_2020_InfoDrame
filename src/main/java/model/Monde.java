package model;

import engine.controller.Cmd;
import exception.PacmanException;
import model.personnages.*;
import model.plateau.Case;
import model.plateau.Position;
import model.plateau.Labyrinthe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * @author Tabary
 */
public class Monde {
    private final Pacman pacman;
    private final Fantome fantomePisteur;
    private final Fantome fantomePiegeurD;
    private final Fantome fantomePiegeurG;
    private final Fantome fantomePeureux;
    private int score;
    private final Labyrinthe labyrinthe;
    private final Collection<Personnage> personnages;
    private final PropertyChangeSupport pcs;
    private final Random random;

    /**
     * Initialisation du monde à partir d'un labyrinthe.
     * @param labyrinthe le plateau de jeu initial.
     */
    Monde(Labyrinthe labyrinthe){
        random = new Random();
        this.labyrinthe = labyrinthe;
        pacman = new Pacman(this, labyrinthe.getPositionInitialPacman());
        fantomePisteur = new FantomePisteur(this, getPosSpawnFantome(), pacman.getPosition());
        fantomePiegeurD = new FantomePiegeur(this, getPosSpawnFantome(), pacman.getPosition(), "droite");
        fantomePiegeurG = new FantomePiegeur(this, getPosSpawnFantome(), pacman.getPosition(), "gauche");
        fantomePeureux = new FantomePeureux(this, getPosSpawnFantome(), pacman.getPosition());
        score = 0;
        personnages = new ArrayList<>();
        personnages.add(pacman);
        personnages.add(fantomePisteur);
        personnages.add(fantomePiegeurD);
        personnages.add(fantomePiegeurG);
        personnages.add(fantomePeureux);
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * Ajoute un PropertyChangeListener au PropertyChangeSupport.
     * @param l de type PropertyChangeListener.
     */
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("score", l);
        pcs.addPropertyChangeListener("vie", l);
    }

    /**
     * Méthode permettant d'obtenir la liste des voisins associés à une position.
     * @param position la position pour laquelle on souhaite obtenir les voisins.
     * @return les voisins associés à une case donnée.
     */
    public Case[] getVoisins(Position position) {
        Case[] res = new Case[4];

        res[0] = labyrinthe.getCasePlateau(position.getX()-1, position.getY());     // à gauche

        res[2] = labyrinthe.getCasePlateau(position.getX()+1, position.getY());     // à droite

        res[1] = labyrinthe.getCasePlateau(position.getX(), position.getY()-1);     // en haut

        res[3] = labyrinthe.getCasePlateau(position.getX(), position.getY()+1);     // en bas

        return res;
    }

    /**
     * Met à jour la direction de Pacman.
     * @param commande la commande saisie par l'utilisateur.
     */
    public void setJoueurDir(Cmd commande) {
        if(commande.equals(Cmd.LEFT) || commande.equals(Cmd.UP) || commande.equals(Cmd.RIGHT) || commande.equals(Cmd.DOWN)){
            pacman.setDir(commande);
        }
    }

    /**
     * Calcule la prochaine étape du jeu.
     */
    public void nextStep(){
        // TODO : tester la condition de victoire (plus de pièces sur le plateau)
        // TODO : tester la condition de défaite (plus de vie disponible)
        // Déplace tous les personnages
        for (Personnage p : personnages) p.move();
        // Résout les conflits de positions entre les personnages
        for (Personnage p : personnages) p.attack();
        // Réduits les cooldowns des personnages
        for (Personnage p : personnages) p.live();
    }

    /**
     * Méthode permettant d'augmenter le score du joueur en fonction des pièces récupérées.
     * @param i les points ajoutés au score lorsque le joueur récupère une pièce.
     */
    public void increaseScore(int i) {
        int scoreOld = this.score;
        score += i;
        if(score%10000 == 0) {
            int oldVie = pacman.getVie();
            pacman.increaseVie();
            pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
        }
        pcs.firePropertyChange("score", scoreOld, this.score);
    }

    /**
     * Méthode qui enlève une vie au joueur (pacman).
     */
    public void decreasedVie() {
        for(Personnage p : personnages) p.resetPosition();
        int oldVie = pacman.getVie();
        pacman.decreasedVie();
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }

    public int getLargeur() {
        return labyrinthe.getLargeur();
    }

    public int getHauteur() {
        return labyrinthe.getHauteur();
    }

    public Case getCaseAt(Position position){
        return labyrinthe.getCasePlateau(position);
    }

    /**
     * Regarde à la position passée en paramètre.
     * S'il y a une pièce, la retire du labyrinthe.
     * @param position la position de la pièce ramassée.
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
     * Méthode listant les personnages à une position.
     * @param position la position où on regarde.
     * @return la liste des personnages à la position demandée.
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
     * Tue un personnage et le remet à sa position de départ.
     * @param personnage le personnage qui est mort.
     */
    public void kill(Personnage personnage) {
        System.out.println("AAAAAAAAAAAAAHHHHHHHHH " + personnage.getClass().getSimpleName() + " EST MORT ! ");
        personnage.die();
    }

    /**
     * Retourne une position de spawn pour un fantôme aléatoire parmis toutes disponible.
     * @return Position une position de spawn de fantôme.
     */
    public Position getPosSpawnFantome() throws PacmanException {
        List<Position> positions = labyrinthe.getPosInitFantome();
        return new Position(positions.get(random.nextInt(positions.size())));
    }

    /**
     * Méthode permettant d'obtenir la position initiale (lors du chargement du labyrinthe) de Pacman.
     * @return la position initiale de Pacman.
     */
    public Position getPosInitPacman() {
        return labyrinthe.getPositionInitialPacman();
    }

    /**
     * Méthode permettant d'obtenir le labyrinthe
     * @return le labyrinthe actuel
     */
    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

    /**
     * Méthode permettant d'obtenir Pacman
     * @return Le seul et unique Pacman de la partie !
     */
    public Pacman getPacman() {
        return pacman;
    }

    /**
     * Méthode permettant d'obtenir la série d'élimination des fantômes par Pacman
     * @return la série d'élimination des fantômes par Pacman
     */
    public int getPacmanStreak(){
        return pacman.getStreak();
    }

    /**
     * Méthode permettant d'obtenir la liste des personnages
     * @return la liste des personnages
     */
    public Collection<Personnage> getPersonnages() {
        return new ArrayList<>(personnages);
    }
}
