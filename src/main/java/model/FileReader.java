package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<String> validEntry = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "P"));

        try {
            File file = new File(chemin);

            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

            String line;
            String temp;
            int x = 0;
            while((line = reader.readLine()) != null){
                if(!line.startsWith("//")){
                    for (int y = 0; y < 28; y++) {
                        temp = String.valueOf(line.charAt(y));
                        if(validEntry.contains(temp)){
                            plateau[x][y] = Integer.parseInt(temp);
                        }
                        else{
                            //TODO :: faire remonter l'exception ?
                            System.out.println("Erreur lecture: Plateau invalide");
                            return null;
                        }
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
