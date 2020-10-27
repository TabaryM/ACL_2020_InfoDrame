package model;

import engine.controller.Position;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Tabary
 */
public class Labyrinthe {

    private char[][] plateau;
    private Position posInitPacman;
    private Collection<Piece> pieces;

    public Labyrinthe(String path){
        this(FileReader.getInstance().buildPlateau(path));
        pieces = new ArrayList<Piece>();
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                if(plateau[i][j] == 'P'){
                    posInitPacman = new Position(j,i);
                }else if (plateau[i][j] == '2'){
                    pieces.add(new PieceScore(j, i));
                }else if (plateau[i][j] == '3'){
                    pieces.add(new PieceAttaque(j, i));
                }
            }
        }
    }

    public Labyrinthe(char[][] plateau){
        this.plateau = plateau;
        pieces = new ArrayList<Piece>();
        System.out.println(this);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                stringBuilder.append(plateau[i][j]);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public int getCasePlateau(int x, int y){
        return plateau[y][x];
    }

    public Position getPositionPacman() {
        return posInitPacman;
    }

    /**
     * Méthode permettant de retrouver le type de pièce située à la position indiquée par les coordonnées i et j,
     * représentant respectivement les axes x et y.
     * @param i entier représentant la position des pièces sur l'axe des x
     * @param j entier représentant la position des pièces sur l'axe des y
     * @return la pièce se trouvant à la position (i,j).
     */
    public Piece getPiece(int i, int j){
        Piece piece = null;
        for (Piece p : pieces){
            if (p.getX() == i && p.getY() == j){
                piece = p;
            }
        }
        return piece;
    }

    /**
     * Méthode permettant de supprimer les pièces que PacMan récupère, une par une.
     * @param i entier représentant la position des pièces sur l'axe des x
     * @param j entier représentant la position des pièces sur l'axe des y
     */
    public void deletePiece(int i, int j){
        Piece removedPiece = null;
        for (Piece p : pieces){
            if (p.getX() == i && p.getY() == j){
                removedPiece = p;
            }
        }
        if (removedPiece != null) {
            pieces.remove(removedPiece);
        }
    }
}
