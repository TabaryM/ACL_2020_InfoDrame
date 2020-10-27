package model;

import engine.controller.Cmd;
import engine.controller.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tabary
 */
public class Monde {
    // TODO : modifier diagramme de classe (déplacement des pieces dans le labyrinthe)
    private final Pacman pacman;
    private int score;
    private final Labyrinthe labyrinthe;
    private final List<Personnage> personnages;

    // TODO : parametre via lecture de fichier
    Monde(Labyrinthe labyrinthe){
        this.labyrinthe = labyrinthe;
        pacman = new Pacman();
        pacman.setPosition(labyrinthe.getPositionPacman());
        score = 0;
        personnages = new ArrayList<Personnage>();
        personnages.add(pacman);
    }

    public void setPacmanDir(Cmd commande) {
        if(!commande.equals(Cmd.IDLE)){
            pacman.setDir(commande);
        }
    }

    public void nextStep(){
        for (Personnage p : personnages){
            if(personnagePeutAvancer(p)){
                p.move();
                if (labyrinthe.getCasePlateau(p.getPosition().getX(), p.getPosition().getY()) == '2'){
                    increaseScore(10);
                    labyrinthe.deletePiece(p.getPosition().getX(), p.getPosition().getY());
                }
                if (labyrinthe.getCasePlateau(p.getPosition().getX(), p.getPosition().getY()) == '3'){
                    increaseScore(50);
                    labyrinthe.deletePiece(p.getPosition().getX(), p.getPosition().getY());
                }
            }
            System.out.println(p.getPosition() + " status : "+p.getCurrentDirection());
        }
        //System.out.println(labyrinthe);
        System.out.println(score);
    }

    public boolean personnagePeutAvancer(Personnage p){
        boolean tmp = true;
        Position pos = p.getPosition();
        switch (p.getCurrentDirection()){
            case LEFT:
                tmp = labyrinthe.getCasePlateau(pos.getX()-1, pos.getY()) != '1';
                break;
            case DOWN:
                tmp = labyrinthe.getCasePlateau(pos.getX(), pos.getY()+1) != '1';
                break;
            case UP:
                tmp = labyrinthe.getCasePlateau(pos.getX(), pos.getY()-1) != '1';
                break;
            case RIGHT:
                tmp = labyrinthe.getCasePlateau(pos.getX()+1, pos.getY()) != '1';
                break;
        }
        return tmp;
    }

    public void increaseScore(int i) {
        score += i;
    }
}
