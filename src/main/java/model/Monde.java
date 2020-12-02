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
import java.util.*;

/**
 * @author Tabary
 */
public class Monde {
    private final Pacman pacman;
    private final Fantome fantomePisteur1;
    private final Fantome fantomePisteur2;
    private final Fantome fantomePisteur3;
    private int score;
    private int scoreVie;
    private Labyrinthe labyrinthe;
    private final Collection<Personnage> personnages;
    private final PropertyChangeSupport pcs;
    private boolean play = true;
    private final Random random;

    /**
     * Initialisation du monde à partir d'un labyrinthe
     * @param labyrinthe le plateau de jeu initial
     */
    Monde(Labyrinthe labyrinthe){
        random = new Random();
        this.labyrinthe = labyrinthe;
        pacman = new Pacman(this, labyrinthe.getPositionInitialPacman());
        fantomePisteur1 = new FantomePisteur(this, getPosSpawnFantome(), pacman.getPosition());
        fantomePisteur2 = new FantomePisteur(this, getPosSpawnFantome(), pacman.getPosition());
        fantomePisteur3 = new FantomePisteur(this, getPosSpawnFantome(), pacman.getPosition());
        score = 0;
        scoreVie = 0;
        personnages = new ArrayList<>();
        personnages.add(pacman);
        personnages.add(fantomePisteur1);
        personnages.add(fantomePisteur2);
        personnages.add(fantomePisteur3);
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * Change le labyrinthe du monde
     * @param labyrinthe le nouveau labyrinthe du monde
     */
    public void setLabyrinthe(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
        for(Personnage personnage : personnages){
            personnage.resetPosition();
        }
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
        if (play) {
            // Déplace tous les personnages
            for (Personnage p : personnages) p.move();
            // Résout les conflits de positions entre les personnages
            for (Personnage p : personnages) p.attack();
            // Réduits les cooldowns des personnages
            for (Personnage p : personnages) p.live();
        }
    }

    /**
     * Méthode permettant d'augmenter le score du joueur en fonction des pièces récupérées.
     * @param i les points ajoutés au score lorsque le joueur récupère une pièce.
     */
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

    /**
     * Méthode qui enlève une vie au joueur (pacman)
     */
    public void decreasedVie() {
        int oldVie = pacman.getVie();
        pacman.decreaseVie();
        // TODO : gérer les attaques de deux fantômes en même temps (pour pas que Pacman perde deux vie au lieu d'une)
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }

    public void resetAllPosition(){
        for(Personnage p : personnages) p.resetPosition();
    }

    /**
     * Méthode qui ajoute une vie au joueur (pacman)
     */
    public void increaseVie() {
        int oldVie = pacman.getVie();
        pacman.increaseVie();
        pcs.firePropertyChange("vie", oldVie, this.pacman.getVie());
    }

    /**
     * Réinitialise le nombre de vie de Pacman
     */
    public void resetPacmanVie() {
        int oldVie = pacman.getVie();
        pacman.resetVie();
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
        personnage.die();
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    /**
     * Retourne une position de spawn pour un fantôme aléatoire parmis toutes disponible
     * @return Position une position de spawn de fantôme
     */
    public Position getPosSpawnFantome() throws PacmanException {
        List<Position> positions = labyrinthe.getPosInitFantome();
        return new Position(positions.get(random.nextInt(positions.size())));
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

    public int getPacmanStreak(){
        return pacman.getStreak();
    }

    public Collection<Personnage> getPersonnages() {
        return new ArrayList<>(personnages);
    }

    public boolean pacmanWon(){
        return labyrinthe.noPiecesLefts();
    }

    public boolean pacmanLost(){
        return pacman.getVie() < 0;
    }

    public void resetScore() {
        int scoreOld = this.score;
        scoreVie = score = 0;
        pcs.firePropertyChange("score", scoreOld, this.score);
    }
}
