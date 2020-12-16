import model.Piece;
import model.plateau.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrintheTest {

    private Labyrinthe labyrinthe;

    @BeforeEach
    void setUp() {
        labyrinthe = new Labyrinthe("src/main/resources/labyTest2.txt");
    }

    // Test right et boundary
    @Test
    void getCasePlateauCoordonnees() {
        Case testMur = new Mur(2, 2);
        Case testCouloir = new Couloir(4, 0);

        assertEquals(testMur.isMur(), labyrinthe.getCasePlateau(2,2).isMur(), "Oui");
        assertEquals(testMur.isMur(), labyrinthe.getCasePlateau(4,0).isMur(), "Oui");

        assertEquals("(x="+2+"  y="+2+")", labyrinthe.getCasePlateau(2,2).toSring(), "La case trouvée n'est pas la bonne");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(10,-5).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(-5,10).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(10,20).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(-5,-15).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
    }

    @Test
    void getCasePlateauPosition() {
        assertEquals("(x="+2+"  y="+2+")", labyrinthe.getCasePlateau(new Position(2, 2)).toSring(), "La case trouvée n'est pas la bonne");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(new Position(10, -5)).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(new Position(-5, 10)).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(new Position(10, 20)).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
        assertEquals("(x="+0+"  y="+0+")", labyrinthe.getCasePlateau(new Position(-5, -15)).toSring(), "La case hors du tableau à tester ne renvoie pas la bonne case");
    }

    // tests right et boundary
    @Test
    void getPiece() {
        // on cherche des pièces existantes (1 et 2) ou non (3 et 4) pour effectuer les tests
        Piece piece1 = labyrinthe.getPiece(new Position(3, 0));
        Piece piece2 = labyrinthe.getPiece(new Position(1, 3));
        Piece piece3 = labyrinthe.getPiece(new Position(0, 3));
        Piece piece4 = labyrinthe.getPiece(new Position(0, 0));
        assertEquals(50, piece1.getScore(), "La piece attaque trouvée ne retourne pas la bonne valeur de score");
        assertEquals(10, piece2.getScore(), "La piece score trouvée ne retourne pas la bonne valeur de score");
        assertNull(piece3, "Le couloir trouvé est une pièce");
        assertNull(piece4, "Le mur trouvé est une pièce");
    }

    // tests right
    @Test
    void deletePiece() {
        // on trouve une pièce dans le labyrinthe
        Piece piece1 = labyrinthe.getPiece(new Position(3, 0));
        assertEquals(50, piece1.getScore(), "La piece attaque trouvée ne retourne pas la bonne valeur de score");
        labyrinthe.deletePiece(new Position(3, 0));
        Piece piece2 = labyrinthe.getPiece(new Position(3, 0));
        assertNull(piece2, "La piece supprimée existe encore");
    }
}