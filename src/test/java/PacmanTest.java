import engine.controller.Cmd;
import model.Monde;
import model.MondeInterface;
import model.personnages.Pacman;
import model.plateau.*;
import org.junit.jupiter.api.*;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


class PacmanTest {

    Pacman pacman;
    MondeInterface monde;
    static LabyrintheInterface labyrinthe;

    @BeforeAll
    static void init(){
        labyrinthe = new Labyrinthe("src/main/resources/labyTest.txt");
    }

    @BeforeEach
    void setUp() {
        monde = new Monde(labyrinthe);
        pacman = new Pacman(monde, monde.getPosInitPacman());
    }

    // Test Right et Boundary
    @Test
    void increaseStreakFor() {
        int streak = 2;
        for(int i = 0; i < 5; i++) {
            if((int) Math.pow(streak, i) < 8) {
                assertSame((int) Math.pow(streak, i), pacman.getStreak());
            } else {
                assertSame(8, pacman.getStreak());
            }
            pacman.increaseStreak();
        }
    }

    // Test Right et Boundary
    @RepeatedTest(6)
    void increaseStreakJUnit(RepetitionInfo repetitionInfo) {
        int streak = 2;
        for(int i = 0; i < repetitionInfo.getCurrentRepetition()-1; i++){
            pacman.increaseStreak();
        }
        if((int) Math.pow(streak, repetitionInfo.getCurrentRepetition()-1) < 8) {
            assertSame((int) Math.pow(streak, repetitionInfo.getCurrentRepetition()-1), pacman.getStreak());
        } else {
            assertSame(8, pacman.getStreak());
        }
    }

    @Test
    void resetPositionRight() {
        // Création du mock et d'une position dans ce monde mock
        monde = createMock(MondeInterface.class);
        Position posInitPacman = new Position(4, 4);

        // On vérifie que la position dans le mock est acceptée par Pacman
        pacman = new Pacman(monde, posInitPacman);
        assertEquals(posInitPacman, pacman.getPosition());

        // On planifie le mock
        expect(monde.getPosInitPacman()).andReturn(posInitPacman);

        replay(monde);
        // On change la position pour être sûre que le reset n'est pas une procédure vide
        pacman.getPosition().setY(1);
        pacman.getPosition().setX(5);

        // On reset et on vérifie que tout va bien
        pacman.resetPosition();
        verify(monde);

        assertEquals(posInitPacman, pacman.getPosition());
    }

    @Test
    void resetPositionNull(){
        monde = createMock(MondeInterface.class);
        pacman = new Pacman(monde, new Position(1, 1));
        expect(monde.getPosInitPacman()).andReturn(null);
        replay(monde);
        pacman.resetPosition();
        verify(monde);
        assertNull(pacman.getPosition());
        // TODO : faire en sorte de ne jamais avoir de jeu si Pacman n'a pas de position initiale dans le labyrinthe
    }

    @Test
    void teleportRightEnMarchantVersLeBas(){
        // Début mise en place pour un bon teleport
        pacman.setDir(Cmd.DOWN);
        pacman.getPosition().moveDown();
        // Fin mise en place

        Position position = new Position(pacman.getPosition());
        pacman.teleport();
        position.setY(0);
        pacman.getPosition().moveDown();
        assertEquals(position, pacman.getPosition());
    }

    @Test
    void teleportRightEnMarchantVersLaDroite(){
        // Début mise en place pour un bon teleport
        pacman.setDir(Cmd.RIGHT);
        pacman.getPosition().moveRight();
        // Fin mise en place

        Position position = new Position(pacman.getPosition());
        pacman.teleport();
        position.setX(0);
        pacman.getPosition().moveRight();
        assertEquals(position, pacman.getPosition());
    }

    @Test
    void teleportRightLoinDeBords() {
        Position position = new Position(pacman.getPosition());
        pacman.teleport();
        assertEquals(position, pacman.getPosition());
    }

    @Test
    void teleportDansUnMur(){
        // TODO : Test pertinent
        char[][] tab = new char[5][];
        tab[0] = new char[]{'1', '1', '1', '1', '1'};
        tab[1] = new char[]{'F', '0', '0', '0', '0'};
        tab[2] = new char[]{'0', '0', '0', '0', '0'};
        tab[3] = new char[]{'0', '0', '0', '0', '0'};
        tab[4] = new char[]{'0', '0', '0', '0', 'P'};
        Labyrinthe labyrinthe = new Labyrinthe(tab);
        monde = new Monde(labyrinthe);
        pacman = new Pacman(monde, monde.getPosInitPacman());
        pacman.setDir(Cmd.DOWN);
        pacman.teleport();
        pacman.move();
        // Sachant qu'il y a un mur de l'autre côté du monde, le personnage ne devrait pas se téléporter.
        assertEquals(new Position(4, 4), pacman.getPosition());
    }
}
