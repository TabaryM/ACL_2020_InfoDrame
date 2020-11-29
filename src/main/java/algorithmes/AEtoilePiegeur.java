package algorithmes;

import engine.controller.Cmd;
import model.Monde;
import model.plateau.Case;
import model.plateau.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class AEtoilePiegeur extends AEtoile {

    private final String direction;

    public AEtoilePiegeur(Monde monde, Position pacmanPosition, Position fantomePosition, String direction) {
        super(monde, pacmanPosition, fantomePosition);
        this.direction = direction;
    }

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

                        if(direction.equals("droite") && voisin.getX() < fantomePosition.getX()){ // si il va a droite et que le voisin est a sa gauche
                            calcScore+= 5;
                        }
                        else if(!direction.equals("droite") && voisin.getX() > fantomePosition.getX()){
                            calcScore+= 5;
                        }

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
