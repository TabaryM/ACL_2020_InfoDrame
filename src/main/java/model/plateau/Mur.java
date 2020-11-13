package model.plateau;

/**
 * @author Tabary
 */
public class Mur extends Case{
    /**
     * Initialisation d'un mur à une position 2D
     *
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     */
    public Mur(int x, int y) {
        super(x, y);
    }

    @Override
    public int getCoutAcces() {
        return 999;
    }

    @Override
    public String toString() {
        return "░░";
    }
}
