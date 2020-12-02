package dataFactories;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private static BufferedImage fantomePisteurDroite;
    private static BufferedImage fantomePisteurHaut;
    private static BufferedImage fantomePisteurBas;
    private static BufferedImage fantomePisteurGauche;
    private static BufferedImage fantomePeureuxDroite;
    private static BufferedImage fantomePeureuxHaut;
    private static BufferedImage fantomePeureuxBas;
    private static BufferedImage fantomePeureuxGauche;
    private static BufferedImage fantomePiegeurDroite;
    private static BufferedImage fantomePiegeurHaut;
    private static BufferedImage fantomePiegeurBas;
    private static BufferedImage fantomePiegeurGauche;
    private static BufferedImage fantomeFaible;
    private static List<BufferedImage> pacmanMort;
    private static Image pacmanAnim;


    private ImageFactory() {
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
            fantomePisteurDroite = ghostSprite.getSubimage(0, 0, 160, 160);
            fantomePisteurHaut = ghostSprite.getSubimage(190, 0, 160, 160);
            fantomePisteurBas = ghostSprite.getSubimage(0, 190, 160, 160);
            fantomePisteurGauche = ghostSprite.getSubimage(190, 190, 160, 160);
            fantomePeureuxDroite = ghostSprite.getSubimage(400, 0, 160, 160);
            fantomePeureuxHaut = ghostSprite.getSubimage(590, 0, 160, 160);
            fantomePeureuxBas = ghostSprite.getSubimage(400, 190, 160, 160);
            fantomePeureuxGauche = ghostSprite.getSubimage(590, 190, 160, 160);
            fantomePiegeurDroite = ghostSprite.getSubimage(400, 380, 160, 160);
            fantomePiegeurHaut = ghostSprite.getSubimage(590, 380, 160, 160);
            fantomePiegeurBas = ghostSprite.getSubimage(400, 570, 160, 160);
            fantomePiegeurGauche = ghostSprite.getSubimage(590, 570, 160, 160);
            fantomeFaible = spriteAll.getSubimage(946, 0, 210, 210);

            pacmanAnim = new ImageIcon("src/main/resources/images/pacman-eating.gif").getImage();

            BufferedImage temp = ImageIO.read(new File("src/main/resources/images/pacman_sprite.png"));
            chargementAnimationPacmanMort(temp);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargementAnimationPacmanMort(BufferedImage img) {
        pacmanMort = new ArrayList<>();
        pacmanMort.add(img.getSubimage(42, 2, 15, 15));
        int i = 1;
        while (i < 220) {
            pacmanMort.add(img.getSubimage(i, 244, 15, 15));
            i = i + 20;
        }
    }

    public BufferedImage getCoinHautGauche() {
        return coinHautGauche;
    }

    public BufferedImage getCoinHautDroit() {
        return coinHautDroit;
    }

    public BufferedImage getCoinBasGauche() {
        return coinBasGauche;
    }

    public BufferedImage getCoinBasDroit() {
        return coinBasDroit;
    }

    public BufferedImage getMurHorizontal() {
        return murHorizontal;
    }

    public BufferedImage getMurVertical() {
        return murVertical;
    }

    public BufferedImage getBoutGauche() {
        return boutGauche;
    }

    public BufferedImage getBoutDroite() {
        return boutDroite;
    }

    public BufferedImage getBoutHaut() {
        return boutHaut;
    }

    public BufferedImage getBoutBas() {
        return boutBas;
    }

    public BufferedImage getPacman() {
        return pacman;
    }

    public BufferedImage getPieceScore() {
        return pieceScore;
    }

    public BufferedImage getPieceAttaque() {
        return pieceAttaque;
    }

    public BufferedImage getFantomePisteurDroite() {
        return fantomePisteurDroite;
    }

    public  BufferedImage getFantomePisteurHaut() {
        return fantomePisteurHaut;
    }

    public BufferedImage getFantomePisteurBas() {
        return fantomePisteurBas;
    }

    public BufferedImage getFantomePisteurGauche() {
        return fantomePisteurGauche;
    }


    public  BufferedImage getFantomePeureuxDroite() {
        return fantomePeureuxDroite;
    }

    public  BufferedImage getFantomePeureuxHaut() {
        return fantomePeureuxHaut;
    }

    public  BufferedImage getFantomePeureuxBas() {
        return fantomePeureuxBas;
    }

    public  BufferedImage getFantomePeureuxGauche() {
        return fantomePeureuxGauche;
    }

    public BufferedImage getFantomePiegeurDroite() {
        return fantomePiegeurDroite;
    }

    public BufferedImage getFantomePiegeurHaut() {
        return fantomePiegeurHaut;
    }

    public BufferedImage getFantomePiegeurBas() {
        return fantomePiegeurBas;
    }

    public BufferedImage getFantomePiegeurGauche() {
        return fantomePiegeurGauche;
    }

    public BufferedImage getFantomeFaible() {
        return fantomeFaible;
    }

    public BufferedImage getPacmanAnim() {
        BufferedImage image = drawAnim(pacmanAnim);
        return image;
    }


    public List<BufferedImage> getPacmanMort() {
        return pacmanMort;
    }


    public BufferedImage drawAnim(Image anim) {
        BufferedImage image = new BufferedImage(
                anim.getWidth(null),
                anim.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();
        g.drawImage(anim, 0, 0, null);
        g.dispose();
        return image;
    }

    public static ImageFactory getInstance() {
        return instance;
    }


}
