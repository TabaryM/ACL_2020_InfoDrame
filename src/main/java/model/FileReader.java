package model;

import exception.PlateauException;

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
    public char[][] buildPlateau(String chemin) {
        ArrayList<Character> validEntry = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', 'P', 'F', 'x', 'y'));
        char[][] plateau = null;
        try {
            File file = new File(chemin);

            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

            String line;
            char temp;
            int x = 0;
            line = reader.readLine();
            int nbLignes = Integer.parseInt(line.split(" ")[0]);
            line = reader.readLine();
            int nbColonnes = Integer.parseInt(line.split(" ")[0]);
            plateau = new char[nbLignes][nbColonnes];

            int commentCount=2;
            while((line = reader.readLine()) != null){
                if(!line.startsWith("//")){
                    if(x >= nbLignes){
                        throw new PlateauException("Erreur plateau invalide : nombre de lignes invalide !");
                    }
                    for (int y = 0; y < nbColonnes; y++) {
                        temp = line.charAt(y);
                        if(validEntry.contains(temp)){
                            plateau[x][y] = temp;
                        }
                        else{
                            throw new PlateauException("Erreur plateau invalide : ligne:"+(x+commentCount)+" colonne:"+y);
                        }
                    }
                    x++;
                }
                else{
                    commentCount++;
                }
            }

            //verifie que les portails sont valides (que l'opposé est aussi pas un mur )
            for(int ligne = 0; ligne < nbLignes; ligne++){
                for(int colonne = 0 ; colonne < nbColonnes; colonne++){
                    if(plateau[ligne][colonne] =='0'){
                        if(ligne==0 && plateau[nbLignes-1][colonne] =='1'){
                            throw new PlateauException("Erreur plateau invalide (portail): ligne:"+(ligne+commentCount+1)+" colonne:"+colonne);
                        }
                        else if(ligne==nbLignes-1 && plateau[0][colonne] =='1'){
                            throw new PlateauException("Erreur plateau invalide (portail): ligne:"+(ligne+commentCount+1)+" colonne:"+colonne);
                        }
                        else if(colonne==0 && plateau[ligne][nbColonnes-1] =='1'){
                            throw new PlateauException("Erreur plateau invalide (portail): ligne:"+(ligne+commentCount+1)+" colonne:"+colonne);
                        }
                        else if(colonne==nbColonnes-1 && plateau[ligne][0] =='1'){
                            throw new PlateauException("Erreur plateau invalide (portail): ligne:"+(ligne+commentCount+1)+" colonne:"+colonne);
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return plateau;
    }
}
