package model.plateau;

/**
 * @author Tabary
 */
public class Couloir extends Case{
    /**
     * Initialisation d'une case vide (un couloir) à une position 2D
     *
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     */
    public Couloir(int x, int y) {
        super(x, y);
    }

    @Override
    public int getCoutAcces() {
        return 1;
    }

    @Override
    public String toString() {
        return "  ";
    }
}
