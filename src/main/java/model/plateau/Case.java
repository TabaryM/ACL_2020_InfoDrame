package model.plateau;

/**
 * @author Tabary
 */
public abstract class Case extends Position{

    /**
     * Initialisation d'une case à une position 2D
     *
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     */
    public Case(int x, int y) {
        super(x, y);
    }

    public abstract double getCoutAcces();

    public abstract boolean isMur();
}
