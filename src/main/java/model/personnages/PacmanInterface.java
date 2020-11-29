package model.personnages;

import engine.controller.Cmd;
import exception.PositionException;
import model.plateau.Position;

/**
 * @author Tabary
 */
public interface PacmanInterface {
    /**
     * Procédure qui met à jour les différents états du personnage (endormis, apeuré, agressif etc...)
     */
    void live();

    /**
     * Procédure de déplacement du personnage
     */
    void move();

    /**
     * Procédure qui résout les attaques entre le personnage actuel et les ennemis qui sont sur la même case que lui
     */
    void attack();

    /**
     * Fixe la position du personnage
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     * @throws PositionException si une des coordonné est hors du plateau
     *                           (inférieur à 0, ou supérieur à la taille du plateau)
     */
    void setPosition(int x, int y) throws PositionException;

    /**
     * Fixe la position du personnage
     * @param position la nouvelle position du personnage
     * @throws PositionException si la position n'est pas dans le plateau de jeu
     */
    void setPosition(Position position) throws PositionException;

    /**
     * Retourne la position du personnage
     */
    Position getPosition();

    /**
     * Retourne la direction actuelle du Personnage
     */
    Cmd getCurrentDirection();

    /**
     * Réinitialise la position du personnage
     */
    void resetPosition();

    /**
     * Procédure qui applique tous les effets de mort du personnage (exemple : augmentation du score, réinitialisation de la position du personnage, etc...)
     */
    void die();

    /**
     * Retourne le score procuré par le meurtre du personnage
     */
    int getScore();

    /**
     * Retourne vrai si le personnage est Pacman, faux sinon
     */
    boolean isPacman();
}
