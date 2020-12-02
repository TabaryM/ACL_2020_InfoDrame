package algorithmes;

import model.Monde;
import model.plateau.Case;
import model.plateau.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Roberge-Mentec Corentin
 */

public abstract class AEtoile {

    protected Monde monde;
    protected HashMap<Position,Position> predecesseur;
    protected ArrayList<ArrayList<Integer>> coutChemin;
    protected ArrayList<ArrayList<Integer>> meilleurChemin;
    protected ArrayList<Position> chemin;
    protected Position pacmanPosition;
    protected Position fantomePosition;
    protected Position courant;
    protected Case caseCourante;
    protected ArrayList<Position> caseOuverte;

    /**
     * Constructeur de la superclasse de l'algorithme de recherche de chemin A*.
     * @param monde le monde dans lequel on évolue.
     * @param pacmanPosition la position du Pacman à atteindre.
     * @param fantomePosition la position actuelle du fantôme.
     */
    public AEtoile(Monde monde, Position pacmanPosition, Position fantomePosition){
        this.monde = monde;
        this.pacmanPosition = pacmanPosition;
        this.fantomePosition = fantomePosition;
        initAEtoile();
    }

    /**
     * Initialisation de l'algorithme A*.
     */
    public void initAEtoile(){
        caseOuverte = new ArrayList<>();
        predecesseur = new HashMap<>();
        coutChemin = new ArrayList<>();
        meilleurChemin = new ArrayList<>();

        // initialiser les colonnes
        for(int i = 0; i < monde.getHauteur(); i++){
            coutChemin.add(new ArrayList<Integer>());
            meilleurChemin.add(new ArrayList<Integer>());
        }
        // initialiser les lignes
        for(int i = 0; i < monde.getHauteur(); i++) {
            for (int j = 0; j < monde.getLargeur(); j++) {
                coutChemin.get(i).add(Integer.MAX_VALUE-1);
                meilleurChemin.get(i).add(Integer.MAX_VALUE-1);
            }
        }
    }

    /**
     * Appel de la résolution du labyrinthe lorsque les fantômes attaquent.
     */
    public void resoudreLabyAttaque(){
        resoudreAEtoile(pacmanPosition);
    }

    /**
     * Résolution de la recherche de chemin de A* par les fantômes.
     * @param but le but à atteindre (ici Pacman).
     */
    public abstract void resoudreAEtoile(Position but);

    /**
     * Résolution du labyrinthe lorsque les fantômes sont vulnérables face à Pacman.
     */
    public void resoudreLabyFuite(){
        initAEtoile();

        Position opposePacman = new Position(monde.getLargeur() - pacmanPosition.getX(), monde.getHauteur() - pacmanPosition.getY());
        Random rand = new Random();
        while(monde.getCaseAt(opposePacman).isMur() || getBirdFlyDist(pacmanPosition, opposePacman) < (0.3*(Math.sqrt(Math.pow(monde.getLargeur(), 2)+Math.pow(monde.getHauteur(), 2))))
         || opposePacman.getX() < 0 || opposePacman.getX() > monde.getHauteur()-1 || opposePacman.getY() < 0 ||opposePacman.getY() > monde.getLargeur()-1){
            opposePacman.setX(rand.nextInt(monde.getLargeur()));
            opposePacman.setY(rand.nextInt(monde.getHauteur()));
        }

        resoudreAEtoile(opposePacman);
    }

    /**
     * Méthode permettant le calcul de la distance à vol d'oiseau d'un point a à un point b.
     * @param a la position de départ pour le calcul.
     * @param b la position d'arrivée pour le calcul.
     * @return la distance à vol d'oiseau d'un point donné a à un poiint donné b.
     */
    public Integer getBirdFlyDist(Position a, Position b){
        return (int) Math.sqrt((Math.pow(Math.abs(a.getX()-b.getX()),2)+Math.pow(Math.abs(a.getY()-b.getY()),2)));
    }

    /**
     * Reconstruction du chemin à emprunter par le fantôme pour atteindre le Pacman.
     * @param courant la position actuellement regardée.
     */
    public void reconstruireChemin(Position courant){
        chemin = new ArrayList<>();
        chemin.add(courant);
        while(predecesseur.get(courant) != null){
            courant = predecesseur.get(courant);
            chemin.add(courant);
        }
    }

    /**
     * Méthode permettant d'obtenir la prochaine case que le fantôme va emprunter.
     * @return la prochaine case empruntée par le fantôme.
     */
    public Position getProchaineCaseDuChemin(){
        Position aCase;

        // On test si il reste du chemin entre le fantôme et Pacman
        if (chemin.size() > 1) {
            aCase = this.chemin.get(chemin.size() - 2);
        }else{
            aCase = chemin.get(0);
        }
        return aCase;
    }

}