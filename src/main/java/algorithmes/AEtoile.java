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
    private ArrayList<Position> caseFermee;
    private Position courant;
    private boolean found;


    public AEtoile(Monde monde, Position pacmanPosition, Position fantomePosition){
        this.monde = monde;
        this.pacmanPosition = pacmanPosition;
        this.fantomePosition = fantomePosition;
    }

    public void initResoudreLabyByStep(){
        caseOuverte = new ArrayList<>();
        caseFermee = new ArrayList<>();
        predecesseur = new HashMap<>();
        coutChemin = new ArrayList<>();
        meilleurChemin = new ArrayList<>();
        //set the columns
        for(int i = 0; i < monde.getCote(); i++){
            coutChemin.add(new ArrayList<Integer>());
            meilleurChemin.add(new ArrayList<Integer>());
        }
        //fill the maze
        for(int i = 0; i < monde.getCote(); i++) {
            for (int j = 0; j < monde.getCote(); j++) {
                coutChemin.get(i).add(Integer.MAX_VALUE-1);
                meilleurChemin.get(i).add(Integer.MAX_VALUE-1);
            }
        }
        coutChemin.get(fantomePosition.getX()).set(fantomePosition.getY(),0);
        meilleurChemin.get(fantomePosition.getX()).set(fantomePosition.getY(),getBirdFlyDist(fantomePosition));
        caseOuverte.add(fantomePosition);
        courant = fantomePosition;
        found = false;
    }

    public void resoudreLabyByStep(){
        ArrayList<Case> voisins = new ArrayList<>();
        Case temp;
        int calcScore;
        if(!caseOuverte.isEmpty()){
            getBestCase();
            if(pacmanPosition.getX() == courant.getX() && pacmanPosition.getY() == courant.getY()){
                reconstruireChemin(courant);
                found = true;
                return;
            }
            caseOuverte.remove(courant);
            caseFermee.add(courant);
            voisins.addAll(Arrays.asList(monde.getVoisins(courant)));
            for(Case voisin : voisins){
                temp = voisin;
                calcScore = coutChemin.get(courant.getX()).get(courant.getY()) + (int) Math.pow(temp.getCoutAcces(),2);
                if(!voisin.getClass().getSimpleName().equals("Wall")){
                    if(calcScore < coutChemin.get(voisin.getX()).get(voisin.getY())){
                        predecesseur.put(voisin,courant);
                        coutChemin.get(voisin.getX()).set(voisin.getY(),calcScore);
                        meilleurChemin.get(voisin.getX()).set(voisin.getY(),(int) (calcScore + Math.pow(getBirdFlyDist(voisin),1.7)));
                        if(!caseOuverte.contains(voisin)){
                            caseOuverte.add(voisin);
                        }
                    }
                }
            }
        }
    }

    public Position getBestCase(){
        int minValue = Integer.MAX_VALUE;
        for(Position p :caseOuverte){
            if(meilleurChemin.get(p.getX()).get(p.getY()) < minValue){
                minValue = meilleurChemin.get(p.getX()).get(p.getY());
                courant = p;
            }
        }
        return courant;
    }

    public ArrayList<Position> getCaseOuverte(){
        return caseOuverte;
    }

    public ArrayList<Position> getCaseFermee(){
        return caseFermee;
    }

    public boolean isFound(){
        return found;
    }

    public void resoudreLaby(){
        caseOuverte = new ArrayList<Position>();
        predecesseur = new HashMap<Position, Position>();
        coutChemin = new ArrayList<ArrayList<Integer>>();
        meilleurChemin = new ArrayList<ArrayList<Integer>>();
        Position courant;
        ArrayList<Case> voisins = new ArrayList<>();
        Case temp;
        //set the columns
        for(int i = 0; i < monde.getCote(); i++){
            coutChemin.add(new ArrayList<Integer>());
            meilleurChemin.add(new ArrayList<Integer>());
        }
        //fill the maze
        for(int i = 0; i < monde.getCote(); i++) {
            for (int j = 0; j < monde.getCote(); j++) {
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
            int minValue = Integer.MAX_VALUE;
            for(Position p :caseOuverte){
                if(meilleurChemin.get(p.getY()).get(p.getY()) < minValue){
                    minValue = meilleurChemin.get(p.getX()).get(p.getY());
                    tmp = p;
                }
            }
            courant = tmp;
            if(pacmanPosition.getX().equals(courant.getX()) && pacmanPosition.getY().equals(courant.getY())){
                reconstruireChemin(courant);
                return;
            }
            caseOuverte.remove(courant);
            voisins.addAll(Arrays.asList(monde.getVoisins(courant)));
            temp = monde.getCaseAt(courant);
            calcScore = coutChemin.get(courant.getX()).get(courant.getY()) + temp.getCoutAcces();
            for(Case voisin : voisins){
                if(!voisin.getClass().getSimpleName().equals("Mur")){
                    if(calcScore < coutChemin.get(voisin.getX()).get(voisin.getY())){
                        predecesseur.put(voisin,courant);
                        coutChemin.get(voisin.getX()).set(voisin.getY(),calcScore);
                        meilleurChemin.get(voisin.getX()).set(voisin.getY(),calcScore + getBirdFlyDist(voisin));
                        if(!caseOuverte.contains(voisin)){
                            caseOuverte.add(voisin);
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
    }

    public ArrayList<Position> getChemin(){
        return this.chemin;
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