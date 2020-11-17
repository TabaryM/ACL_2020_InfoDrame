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
    private static BufferedImage pacman;
    private static BufferedImage pieceScore;
    private static BufferedImage pieceAttaque;

    public ImageFactory() {
        try {
            BufferedImage sprite = ImageIO.read(new File("src/main/resources/images/maze.png"));
            pacman = ImageIO.read(new File("src/main/resources/images/Pacman.png"));
            coinHautGauche = sprite.getSubimage(100, 4, SPRITE_SIZE, SPRITE_SIZE);
            coinHautDroit  = sprite.getSubimage(164, 4, SPRITE_SIZE, SPRITE_SIZE);
            coinBasGauche  = sprite.getSubimage(100, 68, SPRITE_SIZE, SPRITE_SIZE);
            coinBasDroit = sprite.getSubimage(164, 68, SPRITE_SIZE, SPRITE_SIZE);
            murHorizontal = sprite.getSubimage(248, 4, SPRITE_SIZE, SPRITE_SIZE);
            murVertical =  sprite.getSubimage(196, 24, SPRITE_SIZE, SPRITE_SIZE);
            pieceScore = sprite.getSubimage(300, 76, 8, 8);
            pieceAttaque = sprite.getSubimage(231, 71, 18, 18);



        } catch (IOException e) {
            e.printStackTrace();
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

    public BufferedImage getPacman() {
        return pacman;
    }

    public BufferedImage getPieceScore() {
        return pieceScore;
    }

    public BufferedImage getPieceAttaque() {
        return pieceAttaque;
    }

    public static ImageFactory getInstance() {
        return instance;
    }
}
