import engine.controller.Cmd;
import model.Monde;
import model.MondeInterface;
import model.personnages.Pacman;
import model.plateau.Labyrinthe;
import model.plateau.LabyrintheInterface;
import model.plateau.Position;
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

    @Test
    void grabCoin() {
    }

    @Test
    void attack() {
    }

    // Test Right et Boundary
    @Test
    void increaseStreak() {
        int streak = 2;
        for(int i = 0; i < 5; i++) {
            if((int) Math.pow(streak, i) < 8) {
                assertSame((int) Math.pow(streak, i), pacman.getStreak());
            }
            pacman.increaseStreak();
        }
    }

    @Test
    void move() {
    }

    @Test
    void resetPosition() {
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
    void die() {
    }

    @Test
    void increaseVie() {
    }

    @Test
    void isAggressif() {
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
        // TODO : on fait quoi si on veut créer un monde a la mano comme ça ?
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
        assertEquals(new Position(4, 0), pacman.getPosition());
    }
}
