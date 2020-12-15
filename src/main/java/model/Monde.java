package model;

import engine.controller.Cmd;
import exception.PacmanException;
import model.personnages.*;
import model.plateau.Case;
import model.plateau.LabyrintheInterface;
import model.plateau.Position;
import model.personnages.Pacman;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * @author Tabary
 */
public class Monde implements MondeInterface {
    private final PacmanInterface pacman;
    private int score;
    private int scoreVie;
    private LabyrintheInterface labyrinthe;
    private final Collection<PersonnageInterface> personnages;
    private final PropertyChangeSupport pcs;
    private boolean play = true;
    private final Random random;

    /**
     * Initialisation du monde à partir d'un labyrinthe.
     * @param labyrinthe le plateau de jeu initial.
     */
    public Monde(LabyrintheInterface labyrinthe){
        random = new Random();
        this.labyrinthe = labyrinthe;
        personnages = new ArrayList<>();
        pacman = new Pacman(this, labyrinthe.getPositionInitialPacman());
        personnages.add(pacman);
        personnages.add(new FantomePisteur(this, getPosSpawnFantome(), pacman.getPosition()));
        personnages.add(new FantomePiegeur(this, getPosSpawnFantome(), pacman.getPosition(), "droite"));
        personnages.add(new FantomePiegeur(this, getPosSpawnFantome(), pacman.getPosition(), "gauche"));
        personnages.add(new FantomePeureux(this, getPosSpawnFantome(), pacman.getPosition()));
        score = 0;
        scoreVie = 0;
        this.pcs = new PropertyChangeSupport(this);
    }

    // TODO : tester
    /**
     * Change le labyrinthe du monde
     * @param labyrintheInterface le nouveau labyrinthe du monde
     */
    @Override
    public void setLabyrinthe(LabyrintheInterface labyrintheInterface){
        this.labyrinthe = labyrintheInterface;
        for(PersonnageInterface personnageInterface : personnages){
            personnageInterface.resetPosition();
        }
    }

    /**
     * Ajoute un PropertyChangeListener au PropertyChangeSupport.
     * @param l de type PropertyChangeListener.
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("score", l);
        pcs.addPropertyChangeListener("vie", l);
    }

    // TODO : tester
    /**
     * Méthode permettant d'obtenir la liste des voisins associés à une position.
     * @param position la position pour laquelle on souhaite obtenir les voisins.
     * @return les voisins associés à une case donnée.
     */
    @Override
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
    @Override
    public void setJoueurDir(Cmd commande) {
        if(commande.equals(Cmd.LEFT) || commande.equals(Cmd.UP) || commande.equals(Cmd.RIGHT) || commande.equals(Cmd.DOWN)){
            pacman.setDir(commande);
        }
    }

    /**
     * Calcule la prochaine étape du jeu.
     */
    @Override
    public void nextStep(){
        if(play) {
            // Téléporte Pacman si il sort de l'écran
            pacman.teleport();
            // Déplace tous les personnages
            for (PersonnageInterface p : personnages) p.move();
            // Résout les conflits de positions entre les personnages
            for (PersonnageInterface p : personnages) p.attack();
            // Réduits les cooldowns des personnages
            for (PersonnageInterface p : personnages) p.live();
        }
    }

    // TODO : tester
    /**
     * Méthode permettant d'augmenter le score du joueur en fonction des pièces récupérées.
     * @param i les points ajoutés au score lorsque le joueur récupère une pièce.
     */
    @Override
    public void increaseScore(int i) {
        int scoreOld = this.score;
        score += i;
        scoreVie += i;
        if(scoreVie >= 10000){
            increaseVie();
            scoreVie -= 10000;
        }
        pcs.firePropertyChange("score", scoreOld, this.score);
    }

    // TODO : tester
    /**
     * Méthode qui enlève une vie au joueur (pacman).
     */
    @Override
    public void decreasedVie() {
        int oldVie = pacman.getVie();
        pacman.decreaseVie();
        // TODO : gérer les attaques de deux fantômes en même temps (pour pas que Pacman perde deux vie au lieu d'une)
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }

    // TODO : tester
    @Override
    public void resetAllPosition(){
        for(PersonnageInterface p : personnages) p.resetPosition();
    }

    // TODO : tester
    /**
     * Méthode qui ajoute une vie au joueur (pacman)
     */
    @Override
    public void increaseVie() {
        int oldVie = pacman.getVie();
        pacman.increaseVie();
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }

    // TODO : tester
    /**
     * Réinitialise le nombre de vie de Pacman
     */
    @Override
    public void resetPacmanVie() {
        int oldVie = pacman.getVie();
        pacman.resetVie();
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }

    @Override
    public int getLargeur() {
        return labyrinthe.getLargeur();
    }

    @Override
    public int getHauteur() {
        return labyrinthe.getHauteur();
    }

    @Override
    public Case getCaseAt(Position position){
        return labyrinthe.getCasePlateau(position);
    }

    // TODO : tester
    /**
     * Regarde à la position passée en paramètre.
     * S'il y a une pièce, la retire du labyrinthe.
     * @param position la position de la pièce ramassée.
     * @return Si elle existe, la pièce.
     *         Sinon, null.
     */
    @Override
    public Piece grabPieceAt(Position position){
        Piece piece = labyrinthe.getPiece(position);
        if(piece != null){
            labyrinthe.deletePiece(position);
        }
        return piece;
    }

    // TODO : tester
    /**
     * Méthode listant les personnages à une position.
     * @param position la position où on regarde.
     * @return la liste des personnages à la position demandée.
     */
    @Override
    public Collection<PersonnageInterface> getPersonnagesAt(Position position){
        Collection<PersonnageInterface> res = new ArrayList<>();
        for (PersonnageInterface p : personnages){
            if (p.getPosition().equals(position)){
                res.add(p);
            }
        }
        return res;
    }

    /**
     * Tue un personnage et le remet à sa position de départ.
     * @param personnageInterface le personnage qui est mort.
     */
    @Override
    public void kill(PersonnageInterface personnageInterface) {
        personnageInterface.die();
    }

    @Override
    public void setPlay(boolean play) {
        this.play = play;
    }

    // TODO : tester
    /**
     * Retourne une position de spawn pour un fantôme aléatoire parmis toutes disponible.
     * @return Position une position de spawn de fantôme.
     */
    @Override
    public Position getPosSpawnFantome() throws PacmanException {
        List<Position> positions = labyrinthe.getPosInitFantome();
        return new Position(positions.get(random.nextInt(positions.size())));
    }

    // TODO : tester
    /**
     * Méthode permettant d'obtenir la position initiale (lors du chargement du labyrinthe) de Pacman.
     * @return la position initiale de Pacman.
     */
    @Override
    public Position getPosInitPacman() {
        return labyrinthe.getPositionInitialPacman();
    }

    /**
     * Méthode permettant d'obtenir le labyrinthe
     * @return le labyrinthe actuel
     */
    @Override
    public LabyrintheInterface getLabyrinthe() {
        return labyrinthe;
    }

    /**
     * Méthode permettant d'obtenir Pacman
     * @return Le seul et unique Pacman de la partie !
     */
    @Override
    public PacmanInterface getPacman() {
        return pacman;
    }

    /**
     * Méthode permettant d'obtenir la série d'élimination des fantômes par Pacman
     * @return la série d'élimination des fantômes par Pacman
     */
    @Override
    public int getPacmanStreak(){
        return pacman.getStreak();
    }

    /**
     * Méthode permettant d'obtenir la liste des personnages
     * @return la liste des personnages
     */
    @Override
    public Collection<PersonnageInterface> getPersonnages() {
        return new ArrayList<>(personnages);
    }

    @Override
    public boolean pacmanWon(){
        return labyrinthe.noPiecesLefts();
    }

    @Override
    public boolean pacmanLost(){
        return pacman.getVie() < 0;
    }

    @Override
    public void resetScore() {
        int scoreOld = this.score;
        scoreVie = score = 0;
        pcs.firePropertyChange("score", scoreOld, this.score);
    }
}
