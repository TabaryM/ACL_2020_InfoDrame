package model.plateau;

import model.FileReader;
import model.Piece;
import model.PieceAttaque;
import model.PieceScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tabary
 */
public class Labyrinthe {
    private final Case[][] plateau;
    private Position posInitPacman;
    private final List<Position> posInitFantome;
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
        posInitFantome = new ArrayList<>();
        pieces = new HashMap<Position, Piece>();
        for(int i = 0; i < plateau.length; i++){        // Parcours des lignes
            for(int j = 0; j < plateau[i].length; j++){ // Parcours des colonnes

                // Création des objets de Gameplay
                Position tmp = new Position(j, i);
                if(plateau[i][j] == 'P'){
                    // Position du joueur
                    posInitPacman = tmp;
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == 'F') {
                    posInitFantome.add(tmp);
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == '2'){
                    // Position d'une piece de score
                    pieces.put(tmp, new PieceScore(tmp));
                    plateau[i][j] = '0';
                } else if (plateau[i][j] == '3'){
                    // Position d'une piece d'attaque
                    pieces.put(tmp, new PieceAttaque(tmp));
                    plateau[i][j] = '0';
                }

                // Création du monde
                if(plateau[i][j] == '1'){
                    // Mur
                    this.plateau[i][j] = new Mur(j, i);
                } else {
                    // Case inconnue ou couloir
                    this.plateau[i][j] = new Couloir(j, i);
                }
            }
        }
        // TODO Vérifier que toutes les parties importantes sont initialisées :
        // Position de spawn de Pacman
        // Positions de spawns de fantômes
        // Les bords sont tous des murs (sauf portails)
        //System.out.println(this);
    }

    /**
     * Retourne une chaîne de caractère représentant le plateau de jeu
     * @return String le plateau de jeu avec un caractère par case.
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Position tmp = null;
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                tmp = new Position(j, i);
                if(pieces.containsKey(tmp)){
                    stringBuilder.append(pieces.get(tmp).toString());
                } else {
                    stringBuilder.append(plateau[i][j].toString());
                }
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
        if(y < plateau.length  && x < plateau[0].length  && x >= 0 && y >= 0){
            return plateau[y][x];
        }
        return plateau[0][0];
    }

    /**
     * Retourne le caractère à la case demandé
     * @param position la position de la case demandée
     * @return char le caractère à la case du plateau
     */
    public Case getCasePlateau(Position position){
        return getCasePlateau(position.getX(), position.getY());
    }

    /**
     * Retourne la position initiale du joueur
     * @return une copie profonde de la position initiale du joueur
     */
    public Position getPositionInitialPacman() {
        return new Position(posInitPacman);
    }

    /**
     * Retourne la liste des positions initiales des fantômes
     * @return copie profonde (à vérifier) de la liste de position initiale
     */
    public List<Position> getPosInitFantome(){
        return new ArrayList<>(posInitFantome);
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

    public int getLargeur() {
        return plateau.length;
    }

    public int getHauteur() {
        return plateau[0].length;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    /**
     * Test si le labyrinthe contient toujours des pièces
     * @return true si le labyrinthe a 0 pièces,
     *         false sinon.
     */
    public boolean noPiecesLefts(){
        return pieces.size() == 0;
    }
}
