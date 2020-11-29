package model.personnages;

import algorithmes.AEtoile;
import algorithmes.AEtoilePiegeur;
import dataFactories.ImageFactory;
import engine.controller.Cmd;
import model.Monde;
import model.personnages.Fantome;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePiegeur extends Fantome {

    private AEtoile aEtoile;

    public FantomePiegeur(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {
        aEtoile = new AEtoilePiegeur(monde, pacmanPosition, this.position);
    }

    @Override
    protected void moveConcret() {
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

    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePiegeur();
    }
}
