package model.personnages;

import algorithmes.AEtoile;
import algorithmes.AEtoilePeureux;
import dataFactories.ImageFactory;
import engine.controller.Cmd;
import model.Monde;
import model.personnages.Fantome;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePeureux extends Fantome {

    private AEtoile aEtoile;

    public FantomePeureux(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {
        aEtoile = new AEtoilePeureux(monde, pacmanPosition, this.position);
    }

    @Override
    public void moveConcret() {
        if(monde.getPacman().isAggressif()){
            aEtoile.resoudreLabyFuite();
        }
        else{
            aEtoile.resoudreLabyAttaque();
        }

        Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
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
        return ImageFactory.getInstance().getFantomePeureux();
    }
}
