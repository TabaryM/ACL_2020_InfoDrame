package model;

import java.io.*;

/**
 * @author Watelot
 * Classe permettant la lecture d'un plateau de jeu à partir d'un fichier texte
 */
public class FileReader {


    /**
     * Construit le plateau de jeu à partir d'un fichier
     * @param chemin le chemin du fichier à lire
     * @return un tableau de 2 dimensions d'entier
     */
    public int[][] buildPlateau(String chemin){
        int[][] plateau = new int[30][28];
        try {
            File file = new File(chemin);

            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

            String line;
            int x = 0;
            while((line = reader.readLine()) != null){
                if(!line.startsWith("//")){
                    //System.out.println(line);
                    for (int y = 0; y < 28; y++) {
                        plateau[x][y] = Integer.parseInt(String.valueOf(line.charAt(y)));
                    }
                    x++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plateau;
    }
}
