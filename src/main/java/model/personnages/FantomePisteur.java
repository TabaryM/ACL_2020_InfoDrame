package model.personnages;

import algorithmes.AEtoile;
import dataFactories.ImageFactory;
import engine.controller.Cmd;
import model.Monde;
import model.personnages.Fantome;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class FantomePisteur extends Fantome {

    private AEtoile aEtoile;

    public FantomePisteur(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {
        aEtoile = new AEtoile(monde, pacmanPosition, this.position);
        aEtoile.initResoudreLabyByStep();
    }

    @Override
    public void moveConcret() {
        aEtoile.resoudreLaby();
        /*for (Position p : chemin){
            System.out.println(p.getX() + " - " + p.getY());
        }*/
        //System.out.println("\n====================\n");
        Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
        //System.out.println(aCase.getX() + " - " + aCase.getY());
        if (aCase.getX() < position.getX()){
            currentDirection = Cmd.LEFT;
            position.moveLeft();
        } else if (aCase.getX() > position.getX()){
            currentDirection = Cmd.RIGHT;
            position.moveRight();
        } else if (aCase.getY() < position.getY()){
            currentDirection = Cmd.UP;
            position.moveUp();
        } else if (aCase.getY() > position.getY()){
            currentDirection = Cmd.DOWN;
            position.moveDown();
        }
    }

    @Override
    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePisteur();
    }
}
