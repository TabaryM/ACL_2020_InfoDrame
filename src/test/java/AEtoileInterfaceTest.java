import algorithmes.AEtoileInterface;
import algorithmes.AEtoilePeureux;
import algorithmes.AEtoilePisteur;
import model.Monde;
import model.MondeInterface;
import model.plateau.Labyrinthe;
import model.plateau.LabyrintheInterface;
import model.plateau.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class AEtoileInterfaceTest {

    AEtoileInterface aEtoile;
    MondeInterface monde;
    static LabyrintheInterface labyrinthe;

    @BeforeAll
    static void init(){
        labyrinthe = new Labyrinthe("src/main/resources/labyTest.txt");
    }

    @Test
    void initAEtoile() {
        monde = createMock(MondeInterface.class);

        expect(monde.getHauteur()).andReturn(31).times(64); // 2 boucles qui vont de 0 à 31
        expect(monde.getLargeur()).andReturn(28).times(899); // 1 boucle qui fait 31*29

        replay(monde);
        aEtoile = new AEtoilePeureux(monde, new Position(1,1),new Position(15,15));
        verify(monde);
    }

    @Test
    void resoudreAEtoile() {
        monde = new Monde(labyrinthe);
        aEtoile = new AEtoilePeureux(monde, monde.getPosSpawnFantome() ,monde.getPacman().getPosition());
        aEtoile.resoudreLabyAttaque();
        assert(aEtoile.getProchaineCaseDuChemin().getX().equals(2)  && aEtoile.getProchaineCaseDuChemin().getY().equals(3)):"Erreur Aetoile";
    }

}