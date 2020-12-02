package dataFactories;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {
    private static final ImageFactory instance = new ImageFactory();
    protected static final int SPRITE_SIZE = 24;
    private static BufferedImage coinHautGauche;
    private static BufferedImage coinHautDroit;
    private static BufferedImage coinBasGauche;
    private static BufferedImage coinBasDroit;
    private static BufferedImage murHorizontal;
    private static BufferedImage murVertical;
    private static BufferedImage boutGauche;
    private static BufferedImage boutDroite;
    private static BufferedImage boutHaut;
    private static BufferedImage boutBas;
    private static BufferedImage pacman;
    private static BufferedImage pieceScore;
    private static BufferedImage pieceAttaque;
    private static BufferedImage fantomePisteur;
    private static BufferedImage fantomePeureux;
    private static BufferedImage fantomePiegeur;
    private static BufferedImage fantomeFaible;


    public ImageFactory() {
        try {
            BufferedImage sprite = ImageIO.read(new File("src/main/resources/images/maze.png"));
            BufferedImage ghostSprite = ImageIO.read(new File("src/main/resources/images/Ghost.png"));
            BufferedImage spriteAll = ImageIO.read(new File("src/main/resources/images/sprites.png"));
            pacman = ImageIO.read(new File("src/main/resources/images/Pacman.png"));
            coinHautGauche = sprite.getSubimage(100, 4, SPRITE_SIZE, SPRITE_SIZE);
            coinHautDroit  = sprite.getSubimage(164, 4, SPRITE_SIZE, SPRITE_SIZE);
            coinBasGauche  = sprite.getSubimage(100, 68, SPRITE_SIZE, SPRITE_SIZE);
            coinBasDroit = sprite.getSubimage(164, 68, SPRITE_SIZE, SPRITE_SIZE);
            murHorizontal = sprite.getSubimage(248, 4, SPRITE_SIZE, SPRITE_SIZE);
            murVertical =  sprite.getSubimage(196, 24, SPRITE_SIZE, SPRITE_SIZE);
            boutGauche = sprite.getSubimage(228, 4, SPRITE_SIZE, SPRITE_SIZE);
            boutDroite = sprite.getSubimage(296, 4, SPRITE_SIZE, SPRITE_SIZE);
            boutHaut = sprite.getSubimage(196, 4, SPRITE_SIZE, SPRITE_SIZE);
            boutBas = sprite.getSubimage(196, 72, SPRITE_SIZE, SPRITE_SIZE);
            pieceScore = sprite.getSubimage(300, 76, 8, 8);
            pieceAttaque = sprite.getSubimage(231, 71, 18, 18);
            fantomePisteur = ghostSprite.getSubimage(0, 0, 160, 160);
            fantomePeureux = ghostSprite.getSubimage(400, 0, 160, 160);
            fantomePiegeur = ghostSprite.getSubimage(400, 380, 160, 160);
            fantomeFaible = spriteAll.getSubimage(946, 0, 210, 210);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de récupéré l'image du coin en haut à gauche
     * @return le coin en haut à gauche
     */
    public BufferedImage getCoinHautGauche() {
        return coinHautGauche;
    }

    /**
     * Méthode permettant de récupéré l'image du coin en haut à droite
     * @return le coin en haut à droite
     */
    public BufferedImage getCoinHautDroit() {
        return coinHautDroit;
    }

    /**
     * Méthode permettant de récupéré l'image du coin en bas à gauche
     * @return le coin en bas à gauche
     */
    public BufferedImage getCoinBasGauche() {
        return coinBasGauche;
    }

    /**
     * Méthode permettant de récupéré l'image du coin en bas à droite
     * @return le coin en bas à droite
     */
    public BufferedImage getCoinBasDroit() {
        return coinBasDroit;
    }

    /**
     * Méthode permettant de récupéré l'image du mur vertical
     * @return le mur vertical
     */
    public BufferedImage getMurHorizontal() {
        return murHorizontal;
    }

    /**
     * Méthode permettant de récupéré l'image du mur horizontal
     * @return le mur horizontal
     */
    public BufferedImage getMurVertical() {
        return murVertical;
    }

    /**
     * Méthode permettant de récupéré l'image du bout de mur gauche
     * @return le bout de mur gauche
     */
    public BufferedImage getBoutGauche() {
        return boutGauche;
    }

    /**
     * Méthode permettant de récupéré l'image du bout de mur droit
     * @return le bout de mur droit
     */
    public BufferedImage getBoutDroite() {
        return boutDroite;
    }

    /**
     * Méthode permettant de récupéré l'image du bout de mur du haut
     * @return le bout de mur du haut
     */
    public BufferedImage getBoutHaut() {
        return boutHaut;
    }

    /**
     * Méthode permettant de récupéré l'image du bout de mur du bas
     * @return le bout de mur du bas
     */
    public BufferedImage getBoutBas() {
        return boutBas;
    }

    /**
     * Méthode permettant de récupéré l'image de Pacman
     * @return le fameux Pacman !
     */
    public BufferedImage getPacman() {
        return pacman;
    }

    /**
     * Méthode permettant de récupéré l'image de la piece de score
     * @return la piece de score
     */
    public BufferedImage getPieceScore() {
        return pieceScore;
    }

    /**
     * Méthode permettant de récupéré l'image de la piece d'attaque
     * @return la piece d'attaque
     */
    public BufferedImage getPieceAttaque() {
        return pieceAttaque;
    }

    /**
     * Méthode permettant de récupéré l'image du fantôme pisteur
     * @return le fantôme pisteur
     */
    public BufferedImage getFantomePisteur() {
        return fantomePisteur;
    }

    /**
     * Méthode permettant de récupéré l'image du fantôme peureux
     * @return le fantôme peureux
     */
    public BufferedImage getFantomePeureux() {
        return fantomePeureux;
    }

    /**
     * Méthode permettant de récupéré l'image du fantôme piegeur
     * @return le fantôme piegeur
     */
    public BufferedImage getFantomePiegeur() {
        return fantomePiegeur;
    }

    /**
     * Méthode permettant de récupéré l'image du fantome faible
     * @return le fantôme faible
     */
    public BufferedImage getFantomeFaible() {
        return fantomeFaible;
    }

    /**
     * Méthode permettant de récupéré l'instance du singleton
     * @return l'instance
     */
    public static ImageFactory getInstance() {
        return instance;
    }
}
