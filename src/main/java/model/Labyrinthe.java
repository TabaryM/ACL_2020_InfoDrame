package model;

import engine.controller.Position;

/**
 * @author Tabary
 */
public class Labyrinthe {

    private char[][] plateau;
    private Position posInitPacman;

    public Labyrinthe(String path){
        this(FileReader.getInstance().buildPlateau(path));
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                if(plateau[i][j] == 'P'){
                    posInitPacman = new Position(i,j);
                }
            }
        }
    }

    public Labyrinthe(char[][] plateau){
        this.plateau = plateau;
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
}
