import model.Monde;
import model.MondeInterface;
import model.personnages.Fantome;
import model.personnages.FantomePeureux;
import model.personnages.Pacman;
import model.plateau.Case;
import model.plateau.Couloir;
import model.plateau.Labyrinthe;
import model.plateau.LabyrintheInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.*;

class FantomeTest {

    Fantome fantome;
    Pacman pacman;
    MondeInterface monde;
    static LabyrintheInterface labyrinthe;

    @BeforeEach
    void setUp() {
        char[][] tab = new char[5][];
        tab[0] = new char[]{'1', '1', '1', '1', '1'};
        tab[1] = new char[]{'F', '0', '0', '0', '0'};
        tab[2] = new char[]{'0', '0', '0', '0', '0'};
        tab[3] = new char[]{'0', '0', '0', '0', '0'};
        tab[4] = new char[]{'0', '0', '0', '0', 'P'};
        labyrinthe = new Labyrinthe(tab);
        monde = new Monde(labyrinthe);
        pacman = new Pacman(monde,monde.getPosInitPacman());
        fantome = new FantomePeureux(monde, monde.getPosSpawnFantome(), pacman.getPosition());
    }

    @Test
    void nextCase() {
       Case test = labyrinthe.getCasePlateau(1,1);
       fantome.nextCase(test);
       assertEquals(1, fantome.getPosition().getX());
       assertEquals(1, fantome.getPosition().getY());
    }

    @Test
    void nextCaseBouandary() {
        Case test = labyrinthe.getCasePlateau(-1,1);
        fantome.nextCase(test);
        assertEquals(0, fantome.getPosition().getX());
        assertEquals(0, fantome.getPosition().getY());
    }

    @Test
    void attack() {
        Pacman pacman = new Pacman(monde, monde.getPosInitPacman());
        fantome.setPosition(pacman.getPosition().getX(),pacman.getPosition().getY());
        assertEquals(3, pacman.getVie());
        fantome.attack();
        assertEquals(3, pacman.getVie());
        fantome.setPosition(3,3);
        fantome.attack();
        assertEquals(3, pacman.getVie());

    }

    @Test
    void die() {
        fantome.setPosition(3,3);
        fantome.die();
        assertEquals(0, fantome.getPosition().getX());
        assertEquals(1, fantome.getPosition().getY());
        assertEquals(200, monde.getScore());

        fantome.setPosition(3,3);
        fantome.die();
        assertEquals(0, fantome.getPosition().getX());
        assertEquals(1, fantome.getPosition().getY());
        assertEquals(400, monde.getScore());
    }

}