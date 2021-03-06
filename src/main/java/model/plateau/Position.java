package model.plateau;

import java.util.Objects;

/**
 * @author Tabary
 */
public class Position {
    private Integer x;
    private Integer y;

    /**
     * Initialisation d'une position en 2D
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Initialisation d'une position en 2D
     * @param position la position permettant l'initialisation des coordonnées en x et y
     */
    public Position(Position position) {
        this(position.x, position.y);
    }

    /**
     * Retourne la position en abscisse
     * @return x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Fixe la position en abscisse
     * @param x nouvelle abscisse
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Retourne la position en ordonnée
     * @return y
     */
    public Integer getY() {
        return y;
    }

    /**
     * Fixe la position en ordonnée
     * @param y nouvelle ordonnée
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Déplace la position vers la gauche
     * Décrémente l'abscisse de 1
     */
    public void moveLeft(){
        x--;
    }

    /**
     * Déplace la position vers la droite
     * Incrémente l'abscisse de 1
     */
    public void moveRight(){
        x++;
    }

    /**
     * Déplace la position vers le haut
     * Incrémente l'ordonnée de 1
     */
    public void moveUp(){
        y--;
    }

    /**
     * Déplace la position vers le bas
     * Décrémente l'ordonnée de 1
     */
    public void moveDown(){
        y++;
    }

    /**
     * Méthode permettant de modifier les coordonnées d'une position
     * @param position la nouvelle position
     */
    public void setCoord(Position position){
        setX(position.getX());
        setY(position.getY());
    }

    /**
     * Retourne une représentation numérique de la position
     * @return (x, y)
     */
    @Override
    public String toString(){
        return "(x="+x+"  y="+y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x.equals(position.x) &&
                y.equals(position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
