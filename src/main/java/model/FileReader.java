package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Watelot
 * Classe permettant la lecture d'un plateau de jeu à partir d'un fichier texte
 */
public class FileReader {
    public static FileReader instance;

    private FileReader(){

    }

    public static FileReader getInstance() {
        if(instance == null){
            instance = new FileReader();
        }
        return instance;
    }

    /**
     * Construit le plateau de jeu à partir d'un fichier
     * @param chemin le chemin du fichier à lire
     * @return un tableau de 2 dimensions d'entier
     */
    public char[][] buildPlateau(String chemin){
        char[][] plateau = new char[30][28];
        ArrayList<Character> validEntry = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', 'P'));

        try {
            File file = new File(chemin);

            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

            String line;
            char temp;
            int x = 0;
            while((line = reader.readLine()) != null){
                if(!line.startsWith("//")){
                    for (int y = 0; y < 28; y++) {
                        temp = line.charAt(y);
                        if(validEntry.contains(temp)){
                            plateau[x][y] = temp;
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
