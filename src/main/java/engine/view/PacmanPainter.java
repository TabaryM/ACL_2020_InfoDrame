package engine.view;

import dataFactories.ImageFactory;
import model.Monde;
import model.Piece;
import model.personnages.Personnage;
import model.plateau.Case;
import model.plateau.Labyrinthe;
import model.plateau.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter, PropertyChangeListener {


	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 1080;
	protected static final int HEIGHT = 800;
	protected static final int SPRITE_SIZE = 24;
	protected static final int DECALAGE_X = 100;
	protected static final int DECALAGE_Y = 10;
	private int score = 0;
	private int vie = 3;
	private boolean mort = false;
	private BufferedImage laby;
	private int animMort = 0;

	/**
	 * appelle constructeur parent
	 *
	 */
	public PacmanPainter() {
		super();
		this.laby = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im, Monde monde) {
		Graphics2D graphics2D = im.createGraphics();
		Color background = new Color(0, 0, 0);
		graphics2D.setColor(background);
		graphics2D.fillRect(0, 0, getWidth(), getHeight());
		graphics2D.drawImage(this.laby, 100 , 10, null);
		drawFont(graphics2D);
		drawIcon(graphics2D);
		drawPiece(monde, graphics2D);
		if (!mort) {
			drawPersonnage(monde, graphics2D);
		} else {
			monde.setPlay(false);
			drawMort(monde, graphics2D);
		}

		graphics2D.dispose();
	}

	/**
	 * Dessine sur l'image un font
	 * @param graphics2D de type Graphic2D
	 */
	public void drawFont(Graphics2D graphics2D) {
		int height = HEIGHT/10;
		int width = WIDTH - WIDTH/5;
		Color font = new Color(255, 255, 255);

		graphics2D.setColor(font);
		graphics2D.setFont(graphics2D.getFont().deriveFont(25f));
		graphics2D.drawString("Score: " + score, width, height);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	}

	/**
	 * Dessine le nombre de vie du joueur sur l'image
	 * @param graphics2D de type Graphics2D
	 */
	public void drawIcon(Graphics2D graphics2D) {
		int n= this.vie;
		int heigth = HEIGHT - HEIGHT/10;
		try {
			BufferedImage image = ImageIO.read(new File("src/main/resources/images/Pacman.png"));
			BufferedImage imScale = resize(image, 20, 20);
			for (int i = 0; i < n; i++) {
				int x = 20 * i + 10;
				graphics2D.drawImage(imScale, x, heigth, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dessine le labyrinthe
	 * @param monde de type Monde
	 */
	public void drawLaby (Monde monde) {
		Labyrinthe laby = monde.getLabyrinthe();
		Graphics2D graphics2D = this.laby.createGraphics();
		for (int ligne = 0;  ligne < laby.getPlateau().length; ligne++) {
			for (int colonne = 0; colonne < laby.getPlateau()[ligne].length; colonne++) {
				drawWall(ligne, colonne, monde , graphics2D);

			}
		}
	}

	/**
	 * Récupère l'image correspondant à un coin
	 * @param monde de type Monde
	 * @param voisin de type Case[]
	 * @return un BufferedImage
	 */
	private BufferedImage drawWallCoin(Monde monde, Case[] voisin) {
		BufferedImage wall = null;
		Case[] voisinGauche = monde.getVoisins(new Position(voisin[0].getX(), voisin[0].getY()));
		Case[] voisinDroite = monde.getVoisins(new Position(voisin[2].getX(), voisin[2].getY()));


		//Regarde si les diagonales droites et la case en dessous ne sont pas des murs
		if (!voisinDroite[3].isMur() && !voisinDroite[1].isMur() && !voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getCoinBasGauche();

		//Regarde si les diagonales gauches et la case en dessous ne sont pas des murs
		} else if (!voisinGauche[3].isMur() && !voisinGauche[1].isMur() && !voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getCoinBasDroit();

		//Regarde si la diagonales bas gauche n'est pas un mur
		} else if (!voisinGauche[3].isMur()) {
			wall = ImageFactory.getInstance().getCoinHautDroit();

		//Regarde si la diagonal haut gauche n'est pas un mur
		} else if (!voisinGauche[1].isMur()) {
			wall = ImageFactory.getInstance().getCoinBasDroit();

		//Regarde si la diagonal bas droite n'est pas un mur
		} else if (!voisinDroite[3].isMur()) {
			wall = ImageFactory.getInstance().getCoinHautGauche();

		//Regarde si la diagonal haut droite n'est pas un mur
		} else if (!voisinDroite[1].isMur()) {
			wall = ImageFactory.getInstance().getCoinBasGauche();

		}

		return wall;
	}

	/**
	 * Recupère l'image correspondant à un mur spécial
	 * @param voisin de type Case[]
	 * @return un BufferedImage
	 */
	private BufferedImage drawWallSpecial(Case[] voisin) {
		BufferedImage wall = null;

		//Regarde si il y a un mur à droite et en bas
		if (voisin[2].isMur() && voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getCoinHautGauche();

		//Regarde si il y a un mur à droite et en haut
		} else if ( voisin[1].isMur() && voisin[2].isMur()) {
			wall = ImageFactory.getInstance().getCoinBasGauche();

		//Regarde si il y a un mur à gauche et en bas
		} else if ( voisin[0].isMur() && voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getCoinHautDroit();

		//Regarde si il y a un mur à gauche et en haut
		} else if ( voisin[0].isMur() && voisin[1].isMur()) {
			wall = ImageFactory.getInstance().getCoinBasDroit();

		}
		return wall;
	}

	/**
	 * Récupère l'image correspondant à un bord
	 * @param voisin de type Case[]
	 * @return BufferedImage
	 */
	private BufferedImage drawWallBord(Case[] voisin) {
		BufferedImage wall = null;

		//Regarde si il y a pas de mur à gauche ou de mur à droite
		if (!voisin[0].isMur() || !voisin[2].isMur()) {
			wall = ImageFactory.getInstance().getMurVertical();

		//Regarde si il y pas de mur en haut ou en bas
		} else if (!voisin[1].isMur() || !voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getMurHorizontal();
		}

		return wall;
	}

	/**
	 * Récupère l'image correspondant à un mur basique
	 * @param voisin de type Case[]
	 * @return BufferedImage
	 */
	private BufferedImage drawWallBasic(Case[] voisin) {
		BufferedImage wall = null;

		//Regarde si il y a un mur à gauche et à droite
		if (voisin[2].isMur() && voisin[0].isMur()) {
			wall = ImageFactory.getInstance().getMurHorizontal();

		//Regarde si il y a un mur en haut et en bas
		} else if (voisin[1].isMur() && voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getMurVertical();

		}

		return wall;
	}

	/**
	 * Recupère l'image correspondant à un bout de lur
	 * @param voisin de type Case[]
	 * @return un BufferedImage
	 */
	public BufferedImage drawWallBout(Case[] voisin) {
		BufferedImage wall = null;

		//Regarde si il y a un mur à gauche
		if (voisin[0].isMur()) {
			wall = ImageFactory.getInstance().getBoutDroite();

		//Regarde si il y a un mur à droite
		} else if (voisin[2].isMur()) {
			wall = ImageFactory.getInstance().getBoutGauche();

		//Regarde si il y a un mur en haut
		} else if (voisin[1].isMur()) {
			wall = ImageFactory.getInstance().getBoutBas();

		//Regarde si il y a un mur en bas
		} else if (voisin[3].isMur()) {
			wall = ImageFactory.getInstance().getBoutHaut();
		}

		return wall;
	}

	/**
	 * Dessine les mur du labyrinthe
	 * @param ligne de type int
	 * @param colonne de type int
	 * @param monde de type Monde
	 * @param graphics2D de type Graphics2D
	 */
	private void drawWall (int ligne, int colonne , Monde monde, Graphics2D graphics2D) {
		BufferedImage wall = null;

		//Regarde si la case est un mur
		if (monde.getLabyrinthe().getCasePlateau(colonne, ligne).isMur()) {

			Case[] voisin = monde.getVoisins(new Position(colonne, ligne));
			boolean voisinHaut, voisinBas, voisinDroite, voisinGauche;
			voisinHaut = voisin[1].isMur();
			voisinBas = voisin[3].isMur();
			voisinDroite = voisin[2].isMur();
			voisinGauche = voisin[0].isMur();

			//Regarde si tous les voisins de la case sont des murs
			if (voisinHaut && voisinBas && voisinGauche && voisinDroite) {
				wall = drawWallCoin(monde, voisin);

			//Regarde si il y a un mur à gauche et à droite et si il y a un mur en haut ou en bas
			} else if (voisinDroite && voisinGauche && (voisinHaut || voisinBas)) {

				//Cas spécial ou on ce trouve sur le bord
				if (colonne == 0 || colonne == monde.getLabyrinthe().getPlateau()[ligne].length -1) {
					wall = drawWallCoin(monde, voisin);

				} else {
					wall = drawWallBord(voisin);
				}

			//Regarde si il y a un mur en haut et en bas et si il y a un mur à gauche ou à droite
			} else if (voisinHaut && voisinBas && (voisinGauche || voisinDroite) ) {
				wall = drawWallBord(voisin);

			//Regarde si il y a un mur à gauche et à droite ou un mur en haut et en bas
			} else if (voisinGauche && voisinDroite || voisinHaut && voisinBas) {
				wall = drawWallBasic(voisin);

			/* Regarde si il y a un mur en bas et à droite ou un mur en haut et à droite ou
				un mur en bas et à gauche ou un mur en haut et à gauche */
			} else if (voisinBas && voisinDroite || voisinHaut && voisinDroite
						|| voisinBas && voisinGauche || voisinHaut && voisinGauche) {
				wall = drawWallSpecial(voisin);

			} else if (voisinGauche || voisinDroite || voisinHaut || voisinBas) {
				wall = drawWallBout(voisin);
			}
		}

			graphics2D.drawImage(wall,   colonne * 24,     ligne * 24, null);
	}

	/**
	 * Dessine les différents personnage
	 * @param monde de type Monde
	 * @param graphics2D de type graphics2D
	 */
	private void drawPersonnage(Monde monde, Graphics2D graphics2D) {
		BufferedImage spritePerso;
		Collection<Personnage> personnages = monde.getPersonnages();

		for (Personnage personnage : personnages) {

			spritePerso = personnage.getImage();
			if (monde.getPacman().isAggressif() && !personnage.isPacman()) {
				spritePerso = ImageFactory.getInstance().getFantomeFaible();
			}
			BufferedImage persoScale = resize(spritePerso, 20, 20);
			int decalage = (24 - persoScale.getWidth()) / 2;
			int posx = (personnage.getPosition().getX() * SPRITE_SIZE) + DECALAGE_X + decalage;
			int posy = (personnage.getPosition().getY() * SPRITE_SIZE) + DECALAGE_Y + decalage;
			graphics2D.drawImage(persoScale, posx, posy, null);
		}

	}

	/**
	 * Dessine les diffèrentes pièces
	 * @param monde de type Monde
	 * @param graphics2D de type Graphics2D
	 */
	private void drawPiece(Monde monde, Graphics2D graphics2D) {
		Map<Position, Piece> pieces = monde.getLabyrinthe().getPieces();
		BufferedImage sprite;
		int decalage, posx, posy;

		for (Position position : pieces.keySet()) {
			Piece piece = pieces.get(position);
			sprite = piece.getImage();
			decalage = (24 - sprite.getWidth())/2;
			posx = (position.getX() * SPRITE_SIZE) + DECALAGE_X + decalage;
			posy = (position.getY() * SPRITE_SIZE) + DECALAGE_Y + decalage;
			graphics2D.drawImage(piece.getImage(), posx, posy, null);
		}

	}

	public void drawMort(Monde monde, Graphics2D graphics2D) {
		BufferedImage image = ImageFactory.getInstance().getPacmanMort().get(animMort);
		animMort++;
		BufferedImage imgScale = resize(image, 20, 20);
		int decalage = (24 - imgScale.getWidth()) / 2;
		int x = (monde.getPacman().getPosition().getX() * SPRITE_SIZE) + DECALAGE_X + decalage;;
		int y = (monde.getPacman().getPosition().getY() * SPRITE_SIZE) + DECALAGE_Y + decalage;;
		graphics2D.drawImage(imgScale, x, y, null);

		if (animMort == ImageFactory.getInstance().getPacmanMort().size()) {
			mort = false;
			animMort = 0;
			monde.resetAllPosition();
			monde.setPlay(true);

		}


	}


	/**
	 * Redimensionne un image au valeur donner en paramètre
	 * @param img de type BufferedImage
	 * @param newW de type int (Width)
	 * @param newH de type int (Height)
	 * @return un BufferedImage
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("score")) {
			this.score = (Integer) evt.getNewValue();

		} else if (evt.getPropertyName().equals("vie")) {
			int newVie = (Integer) evt.getNewValue();
			if (this.vie > newVie) {
				mort = true;
			}
			this.vie = newVie;

		}
	}
}
