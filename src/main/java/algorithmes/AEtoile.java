package algorithmes;

import model.Monde;
import model.plateau.Case;
import model.plateau.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AEtoile {

    private Monde monde;
    private HashMap<Position,Position> predecesseur;
    private ArrayList<ArrayList<Integer>> coutChemin;
    private ArrayList<ArrayList<Integer>> meilleurChemin;
    private ArrayList<Position> chemin;
    private Position pacmanPosition;
    private Position fantomePosition;

    private ArrayList<Position> caseOuverte;


    public AEtoile(Monde monde, Position pacmanPosition, Position fantomePosition){
        this.monde = monde;
        this.pacmanPosition = pacmanPosition;
        this.fantomePosition = fantomePosition;
    }

    public void resoudreLaby(){
        caseOuverte = new ArrayList<Position>();
        predecesseur = new HashMap<Position, Position>();
        coutChemin = new ArrayList<ArrayList<Integer>>();
        meilleurChemin = new ArrayList<ArrayList<Integer>>();
        Position courant;
        Case temp;
        //set the columns
        for(int i = 0; i < monde.getHauteur(); i++){
            coutChemin.add(new ArrayList<Integer>());
            meilleurChemin.add(new ArrayList<Integer>());
        }
        //fill the maze
        for(int i = 0; i < monde.getHauteur(); i++) {
            for (int j = 0; j < monde.getLargeur(); j++) {
                coutChemin.get(i).add(Integer.MAX_VALUE-1);
                meilleurChemin.get(i).add(Integer.MAX_VALUE-1);
            }
        }
        coutChemin.get(fantomePosition.getX()).set(fantomePosition.getY(),0);
        meilleurChemin.get(fantomePosition.getX()).set(fantomePosition.getY(),getBirdFlyDist(fantomePosition));

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
            if(pacmanPosition.getX().equals(courant.getX()) && pacmanPosition.getY().equals(courant.getY())){
                caseOuverte.clear();
                reconstruireChemin(courant);
            }else {
                caseOuverte.remove(courant);
                voisins.addAll(Arrays.asList(monde.getVoisins(courant)));
                temp = monde.getCaseAt(courant);
                calcScore = coutChemin.get(courant.getX()).get(courant.getY()) + temp.getCoutAcces();
                for (Position voisin : voisins) {
                    if (!voisin.getClass().getSimpleName().equals("Mur")) {
                        if (calcScore < coutChemin.get(voisin.getX()).get(voisin.getY())) {
                            if(!voisin.getX().equals(courant.getX()) &&!voisin.getY().equals(courant.getY())){
                                System.out.println("Aetoile :");
                                System.out.println(courant.getX()+" "+courant.getY()+" ; "+voisin.getX()+" "+voisin.getY());
                            }
                            predecesseur.put(voisin, courant);
                            coutChemin.get(voisin.getX()).set(voisin.getY(), calcScore);
                            meilleurChemin.get(voisin.getX()).set(voisin.getY(), calcScore + getBirdFlyDist(voisin));
                            if (!caseOuverte.contains(voisin)) {
                                caseOuverte.add(voisin);
                            }
                        }
                    }
                }
            }
        }
    }

    public Integer getBirdFlyDist(Position a){
        return (int) Math.sqrt((Math.pow(Math.abs(a.getX()-pacmanPosition.getX()),2)+Math.pow(Math.abs(a.getY()-pacmanPosition.getY()),2)));
    }

    public void reconstruireChemin(Position courant){
        chemin = new ArrayList<Position>();
        chemin.add(courant);
        while(predecesseur.get(courant) != null){
            courant = predecesseur.get(courant);
            chemin.add(courant);
        }
        //System.out.println(chemin);
    }

    public Position getProchaineCaseDuChemin(){
        Position aCase;
        // On test si il reste du chemin jusque Pacman
        if (chemin.size() > 1) {
            aCase = this.chemin.get(chemin.size() - 2);
        }else{
            aCase = chemin.get(0);
        }
        return aCase;
    }

}