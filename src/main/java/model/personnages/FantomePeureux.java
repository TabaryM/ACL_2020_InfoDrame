package model.personnages;

import algorithmes.AEtoilePeureux;
import dataFactories.ImageFactory;
import model.Monde;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePeureux extends Fantome {

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
            aEtoile.resoudreLabyAttaque();
            Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
            move(aCase);
        }
        else {
            aEtoile.resoudreLabyAttaque();
            Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());

            if(aEtoile.getBirdFlyDist(aCase, pacmanPosition) > 4) {
                move(aCase);
            }
            else if(aEtoile.getBirdFlyDist(aCase, pacmanPosition) < 4 ){
                aEtoile.resoudreLabyFuite();
                aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
                move(aCase);
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePeureux();
    }
}
