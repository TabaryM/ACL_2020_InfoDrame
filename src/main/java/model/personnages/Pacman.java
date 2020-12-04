package model.personnages;

import dataFactories.ImageFactory;
import engine.controller.Cmd;
import exception.PacmanException;
import model.Monde;
import model.Piece;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.util.Collection;

import static engine.GameEngineGraphical.TIMESTEP;

/**
 * @author Tabary
 */
public class Pacman extends Personnage implements PacmanInterface {
    private int vie = 3 ;
    private double timeToKill = 0.0;
    private int streak = 1;

    /**
     * Constructeur du personnage Pacman
     * @param monde le monde dans lequel évolue pacman
     * @param position la position actuelle de pacman
     */
    public Pacman(Monde monde, Position position){
        super(monde, new Position(position));
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est en sur place
    }

    /**
     * La série d'élimination des fantômes
     * @return le multiplicateur de core issue de la série d'élimination
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Procédure de vie de Pacman pour une itération de jeu
     */
    @Override
    public void live(){
        // Test collision avec pièce
        grabCoin();
        // Mise a jour des effets
        updateEffects();
    }

    /**
     * Mise à jour du timer pendant lequel pacman peut tuer des fantômes
     */
    private void updateEffects() {
        reduceTimeToKill();
    }

    private void reduceTimeToKill() {
        if(isAggressif()) {
            timeToKill -= TIMESTEP;
        } else {
            timeToKill = 0;
            streak = 1;
        }
    }

    /**
     * Procédure qui permet à Pacman de ramasser une pièce.
     * Si une pièce est ramassée, la retire du monde.
     * Augmente le score du joueur de la valeur de la pièce.
     * Augmente le temps d'attaque du joueur du temps accordé par la pièce.
     */
    private void grabCoin() {
        Piece piece = monde.grabPieceAt(position);
        if(piece != null){
            monde.increaseScore(piece.getScore());
            increaseTimeToKill(piece.getAttackTime());
        }
    }

    /**
     * Augmente le temps d'attaque du joueur par la valeur passée en paramètre
     * @throws PacmanException Si la valeur passée en paramètre est négative.
     *                         Si la somme de val et du champ timeToKill est négative.
     * @param val temps d'attaque supplémentaire ajouté
     */
    private void increaseTimeToKill(double val) throws PacmanException {
        if(val < 0){
            throw new PacmanException("Temps d'attaque négatif");
        }
        if (timeToKill + val < 0){
            throw new PacmanException("Temps d'attaque négatif");
        }
        timeToKill += val * 1000;
    }

    /**
     * Procédure qui vérifie si il y a une situation d'attaque entre Pacman et un fantôme (dans les deux sens)
     */
    public void attack() {
        if(isAggressif()){
            // Test si il y a un fantôme sur la case où est Pacman
            Collection<Personnage> personnages = monde.getPersonnagesAt(position);
            personnages.remove(this);
            for (Personnage p : personnages){
                monde.kill(p);
                increaseStreak();
            }

            // Test si Pacman et un fantôme ont échangé de position
            personnages = monde.getPersonnages();
            personnages.remove(this);
            for(Personnage p : personnages){
                if(p.getPosition().equals(anciennePosition) && p.anciennePosition.equals(getPosition())){
                    monde.kill(p);
                    increaseStreak();
                }
            }
        }
    }

    /**
     * Méthode augmentant le multiplicateur de score issue de la série d'élimination des fantômes
     */
    private void increaseStreak() {
        if(streak < 8){
            streak *= 2;
        }
    }

    /**
     * Procédure de déplacement du joueur.
     * Si la case devant Pacman est un couloir Pacman se déplacera sur cette case,
     * Sinon (si la case est un mur) Pacman restera sur place
     */
    @Override
    public void move() {
        anciennePosition.setCoord(position);
        Case[] voisins = monde.getVoisins(position);
        switch (currentDirection) {
            case LEFT:
                if (voisins[0].getCoutAcces() <= 10) {
                    position.moveLeft();
                }
                break;
            case UP:
                if (voisins[1].getCoutAcces() <= 10) {
                    position.moveUp();
                }
                break;
            case RIGHT:
                if (voisins[2].getCoutAcces() <= 10) {
                    position.moveRight();
                }
                break;
            case DOWN:
                if (voisins[3].getCoutAcces() <= 10) {
                    position.moveDown();
                }
                break;
        }
    }

    /**
     * Ré-initialisation de la position de Pacman
     */
    @Override
    public void resetPosition() {
        Position posInit = monde.getPosInitPacman();
        position.setX(posInit.getX());
        position.setY(posInit.getY());
    }

    /**
     * Méthode permettant la diminution du nombre de vies de Pacman lorsque celui-ci meurt
     */
    @Override
    public void die() {
        monde.decreasedVie();
        setDir(Cmd.IDLE);
    }

    /**
     * Methode qui decrémente de 1 la vie de pacman
     */
    public void decreaseVie() {
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
     */
    public void resetVie() {
        vie = 3;
    }

    /**
     * Methode qui incrémente de 1 la vie de pacman
     */
    public void increaseVie(){
        vie++;
    }

    /**
     * Méthode permettant de définir la direction dans laquelle va Pacman
     * @param commande la nouvelle direction de Pacman
     */
    public void setDir(Cmd commande) {
        currentDirection = commande;
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage img;
        BufferedImage imgRotate;

        if (this.currentDirection.equals(Cmd.IDLE)) {
            img = ImageFactory.getInstance().getPacman();

        } else {
            img = ImageFactory.getInstance().getPacmanAnim();
        }

        switch (this.currentDirection) {

            case UP: {
                imgRotate = rotateImageByDegrees(img, 270);
            }
            break;

            case DOWN: {
                imgRotate = rotateImageByDegrees(img, 90);
            }
            break;

            case LEFT: {
                imgRotate = rotateImageByDegrees(img, 180);
            }
            break;

            default: imgRotate = ImageFactory.getInstance().getPacmanAnim();
            break;


        }

        return imgRotate;
    }

    /**
     * Méthode permettant de dire si Pacman est en train d'attaquer
     * @return vrai si Pacman peut manger les fantômes,
     *         faux sinon.
     */
    public boolean isAggressif(){
        return timeToKill > 0.000001;
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w*cos + h*sin);
        int newHeight = (int) Math.floor(h*cos + w*sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w/2;
        int y = h/2;

        at.rotate(rads, x, y);
        g2D.setTransform(at);
        g2D.drawImage(img, 0, 0, null);
        g2D.setColor(Color.RED);
        g2D.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2D.dispose();

        return rotated;

    }

    /**
     * Méthode qui téléporte Pacman si il traverse le bord de la carte (comme dans le jeu original)
     */
    public void teleport() {
        // Si Pacman est au bord du monde (sans mur), le téléporte de l'autre coté du monde
        if(getPosition().getX().equals(0) && getCurrentDirection().equals(Cmd.LEFT)){
            setPosition(monde.getHauteur(),getPosition().getY());
        }
        else if(getPosition().getX().equals(monde.getHauteur()-1) && getCurrentDirection().equals(Cmd.RIGHT)){
            setPosition(-1,getPosition().getY());
        }
        else if(getPosition().getY().equals(0) && getCurrentDirection().equals(Cmd.UP)){
            setPosition(getPosition().getX(), monde.getLargeur());
        }
        else if(getPosition().getY().equals(monde.getLargeur()-1) && getCurrentDirection().equals(Cmd.DOWN)){
            setPosition(getPosition().getX(), -1);
        }
    }
}
