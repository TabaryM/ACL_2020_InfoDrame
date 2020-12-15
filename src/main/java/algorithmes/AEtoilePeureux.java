package algorithmes;

import model.MondeInterface;
import model.plateau.Case;
import model.plateau.Position;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Roberge-Mentec Corentin
 */

public class AEtoilePeureux extends AEtoile {

    /**
     * Constructeur de la classe fille de AEtoile permettant d'implémenter le fantôme peureux.
     * @param monde le monde dans lequel on évolue.
     * @param pacmanPosition la position du Pacman à atteindre.
     * @param fantomePosition la position actuelle du fantôme.
     */
    public AEtoilePeureux(MondeInterface monde, Position pacmanPosition, Position fantomePosition) {
        super(monde, pacmanPosition, fantomePosition);
    }

    /**
     * Résolution de la recherche de chemin par l'algorithme de A*.
     * @param but le but à atteindre (ici Pacman).
     */
    @Override
    public void resoudreAEtoile(Position but) {
        coutChemin.get(fantomePosition.getX()).set(fantomePosition.getY(),0);
        meilleurChemin.get(fantomePosition.getX()).set(fantomePosition.getY(),getBirdFlyDist(fantomePosition, but));

        caseOuverte.add(fantomePosition);
        Position tmp = fantomePosition;
        int calcScore;
        while(!caseOuverte.isEmpty()){
            ArrayList<Case> voisins = new ArrayList<>();
            int minValue = Integer.MAX_VALUE;
            for(Position p :caseOuverte){
                if(meilleurChemin.get(p.getX()).get(p.getY()) < minValue){
                    minValue = meilleurChemin.get(p.getX()).get(p.getY());
                    tmp = p;
                }
            }
            courant = tmp;
            if(but.getX().equals(courant.getX()) && but.getY().equals(courant.getY())){
                caseOuverte.clear();
                reconstruireChemin(courant);
            }else {
                caseOuverte.remove(courant);
                voisins.addAll(Arrays.asList(monde.getVoisins(courant)));
                caseCourante = monde.getCaseAt(courant);
                calcScore = coutChemin.get(courant.getX()).get(courant.getY()) + caseCourante.getCoutAcces();
                for (Position voisin : voisins) {
                    if (!voisin.getClass().getSimpleName().equals("Mur")) {
                        if (calcScore < coutChemin.get(voisin.getX()).get(voisin.getY())) {
                            predecesseur.put(voisin, courant);
                            coutChemin.get(voisin.getX()).set(voisin.getY(), calcScore);
                            meilleurChemin.get(voisin.getX()).set(voisin.getY(), calcScore + getBirdFlyDist(voisin, but));
                            if (!caseOuverte.contains(voisin)) {
                                caseOuverte.add(voisin);
                            }
                        }
                    }
                }
            }
        }
    }
}
