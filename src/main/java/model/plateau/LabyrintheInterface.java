package model.plateau;

import model.Piece;

import java.util.List;
import java.util.Map;

/**
 * @author Tabary
 */
public interface LabyrintheInterface {
    @Override
    String toString();

    Case getCasePlateau(int x, int y);

    Case getCasePlateau(Position position);

    Position getPositionInitialPacman();

    List<Position> getPosInitFantome();

    Piece getPiece(Position position);

    void deletePiece(Position pos);

    int getLargeur();

    int getHauteur();

    Case[][] getPlateau();

    Map<Position, Piece> getPieces();

    boolean noPiecesLefts();
}
