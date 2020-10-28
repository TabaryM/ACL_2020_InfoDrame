package model;

import engine.controller.Position;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Tabary
 */
public class Labyrinthe {
    private final char[][] plateau;
    private Position posInitPacman;
    private final Collection<Piece> pieces;

    /**
     * Initialise le labyrinthe à partir d'un fichier texte.
     * @param path chemin vers le fichier source du plateau
     */
    public Labyrinthe(String path){
        this(FileReader.getInstance().buildPlateau(path));
    }

    /**
     * Initialise le labyrinthe à partir d'un plateau.
     * @param plateau tableau à deux dimensions contenant les positions des pièces et du joueur
     */
    public Labyrinthe(char[][] plateau){
        this.plateau = plateau;
        pieces = new ArrayList<Piece>();
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                if(plateau[i][j] == 'P'){
                    posInitPacman = new Position(j,i);
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == '2'){
                    pieces.add(new PieceScore(j, i));
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == '3'){
                    pieces.add(new PieceAttaque(j, i));
                    plateau[i][j] = '0';
                }
            }
        }
        System.out.println(this);
    }

    /**
     * Retourne une chaîne de caractère représentant le plateau de jeu
     * @return String le plateau de jeu avec un caractère par case.
     */
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

    /**
     * Retourne le caractère à la case demandé
     * @param x coordonnée en abscisse
     * @param y coordonnée en ordonnée
     * @return char le caractère à la case du plateau
     */
    public char getCasePlateau(int x, int y){
        return plateau[y][x];
    }

    /**
     * Retourne la position initiale du joueur
     * @return Position la position initiale du joueur
     */
    public Position getPositionInitialPacman() {
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
