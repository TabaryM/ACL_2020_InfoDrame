package algorithmes;

import model.plateau.Position;

public interface AEtoileInterface {

    void initAEtoile();

    void resoudreLabyAttaque();

    void resoudreAEtoile(Position but);

    void resoudreLabyFuite();

    Integer getBirdFlyDist(Position a, Position b);

    void reconstruireChemin(Position courant);

    Position getProchaineCaseDuChemin();
}
