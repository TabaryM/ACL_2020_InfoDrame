package model.personnages;

import algorithmes.AEtoile;
import dataFactories.ImageFactory;
import engine.controller.Cmd;
import model.Monde;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePisteur extends Fantome {

    private AEtoile aEtoile;

    public FantomePisteur(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {
        aEtoile = new AEtoile(monde, pacmanPosition, this.position);
    }

    @Override
    public void move() {
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
        return ImageFactory.getInstance().getFantomePisteur();
    }
}
