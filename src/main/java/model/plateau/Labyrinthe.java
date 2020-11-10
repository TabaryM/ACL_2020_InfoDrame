package model.plateau;

import model.FileReader;
import model.Piece;
import model.PieceAttaque;
import model.PieceScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tabary
 */
public class Labyrinthe {
    private final Case[][] plateau;
    private Position posInitPacman;
    private final Map<Position, Piece> pieces;

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
        this.plateau = new Case[plateau.length][plateau[0].length];
        pieces = new HashMap<Position, Piece>();
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                if(plateau[i][j] == 'P'){
                    // Position du joueur
                    posInitPacman = new Position(j,i);
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == '2'){
                    // Position d'une piece de score
                    pieces.put(new Position(i, j), new PieceScore(j, i));
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == '3'){
                    // Position d'une piece d'attaque
                    pieces.put(new Position(i, j), new PieceAttaque(j, i));
                    plateau[i][j] = '0';
                }
                if(plateau[i][j] == '1'){
                    // Mur
                    this.plateau[i][j] = new Mur(i, j);
                } else {
                    // Case inconnue ou couloir
                    this.plateau[i][j] = new Couloir(i, j);
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
                stringBuilder.append(plateau[i][j].toString());
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
    public Case getCasePlateau(int x, int y){
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
     * Méthode permettant de retrouver le type de pièce située à la position position.
     * @param position position de la piece
     * @return la pièce se trouvant à la position demandée.
     */
    public Piece getPiece(Position position){
        return pieces.get(position);
    }

    /**
     * Méthode permettant de supprimer les pièces que PacMan récupère, une par une.
     * @param pos Position de la piece à retirer
     */
    public void deletePiece(Position pos){
        pieces.remove(pos);
    }
}
