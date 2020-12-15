package model.personnages;

import algorithmes.AEtoilePeureux;
import dataFactories.ImageFactory;
import model.MondeInterface;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePeureux extends Fantome implements FantomePeureuxInterface {

    /**
     * Constructeur de la classe fille du fantôme peureux
     * @param monde le monde dans lequel le fantôme évolue
     * @param position la position du fantôme
     * @param pacmanPosition la position de Pacman
     */
    public FantomePeureux(MondeInterface monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    /**
     * Métode permettant le lancement de l'IA A*
     */
    @Override
    public void ia() {
        aEtoile = new AEtoilePeureux(monde, pacmanPosition, this.position);
    }

    /**
     * Méthode permettant de faire bouger les fantômes
     */
    @Override
    public void moveConcret() {
        if(monde.getPacman().isAggressif()){
            aEtoile.resoudreLabyAttaque();
            Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
            nextCase(aCase);
        }
        else {
            aEtoile.resoudreLabyAttaque();
            Case aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());

            if(aEtoile.getBirdFlyDist(aCase, pacmanPosition) > 4) {
                nextCase(aCase);
            }
            else if(aEtoile.getBirdFlyDist(aCase, pacmanPosition) < 4 ){
                aEtoile.resoudreLabyFuite();
                aCase = monde.getCaseAt(aEtoile.getProchaineCaseDuChemin());
                nextCase(aCase);
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage img;

        switch (this.currentDirection) {

            case UP: img = ImageFactory.getInstance().getFantomePeureuxHaut();
            break;

            case DOWN: img = ImageFactory.getInstance().getFantomePeureuxBas();
            break;

            case LEFT: img = ImageFactory.getInstance().getFantomePeureuxGauche();
            break;

            default: img = ImageFactory.getInstance().getFantomePeureuxDroite();
            break;
        }

        return img;
    }
}
