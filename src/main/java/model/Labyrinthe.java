package model;

/**
 * @author Tabary
 */
public class Labyrinthe {

    private int[][] plateau;

    public Labyrinthe(String path){
        this(new FileReader().buildPlateau(path));
    }

    public Labyrinthe(int[][] plateau){
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

}
