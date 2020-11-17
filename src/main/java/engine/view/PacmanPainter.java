package engine.view;

import dataFactories.ImageFactory;
import model.Monde;
import model.Piece;
import model.personnages.Pacman;
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
import java.net.URL;
import java.util.Map;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter, PropertyChangeListener {
//TODO: piece attaque

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 1080;
	protected static final int HEIGHT = 800;
	private int score = 0;
	private int vie = 3;
	private BufferedImage laby;

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
		drawPacman(monde,graphics2D);
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

	public void drawLaby (Monde monde) {
		Labyrinthe laby = monde.getLabyrinthe();
		Graphics2D graphics2D = this.laby.createGraphics();
		for (int ligne = 0;  ligne < laby.getPlateau().length; ligne++) {
			for (int colonne = 0; colonne < laby.getPlateau()[ligne].length; colonne++) {
				drawWall(ligne, colonne, monde , graphics2D);

			}
		}
	}

	public void drawWall (int ligne, int colonne , Monde monde, Graphics2D graphics2D) {
		Case[][] plateau = monde.getLabyrinthe().getPlateau();
		try {
			BufferedImage sprite = ImageIO.read(new File("src/main/resources/images/maze.png"));
			BufferedImage wall;
			if (ligne == 0 && colonne == 0) {
				wall = ImageFactory.getInstance().getCoinHautGauche();

			} else if (ligne == 0 && colonne == plateau[ligne].length - 1) {
				wall = ImageFactory.getInstance().getCoinHautDroit();


			} else if ((ligne == plateau.length - 1) && colonne == 0) {
				wall = ImageFactory.getInstance().getCoinBasGauche();


			} else if ((ligne == plateau.length - 1) && colonne == plateau[ligne].length - 1) {
				wall = ImageFactory.getInstance().getCoinBasDroit();


			} else {
				if (plateau[ligne][colonne].isMur()) {
					Case[] voisin = monde.getVoisins(new Position(colonne, ligne));
					boolean murHaut = false;
					boolean murBas = false;
					boolean murGauche = false;
					boolean murDroite = false;

					if (voisin[0].isMur()) {
						murGauche = true;
					}

					if (voisin[1].isMur()) {
						murHaut = true;
					}

					if (voisin[2].isMur()) {
						murDroite = true;
					}

					if (voisin[3].isMur()) {
						murBas = true;
					}

					if (murDroite && murGauche && murHaut && !murBas) {
						if (colonne == 0) {
							wall = ImageFactory.getInstance().getCoinBasGauche();
						} else if (colonne == plateau[ligne].length - 1) {
							wall = ImageFactory.getInstance().getCoinBasDroit();
						} else {
							wall = ImageFactory.getInstance().getMurHorizontal();
						}

					} else if (murDroite && murGauche && !murHaut && murBas) {
						if (colonne == 0) {
							wall = sprite.getSubimage(100, 4, 24, 24);
						} else if (colonne == plateau[ligne].length - 1) {
							wall = sprite.getSubimage(164, 4, 24, 24);
						} else {
							wall = ImageFactory.getInstance().getMurHorizontal();
						}

					} else if (murDroite && !murGauche && murHaut && murBas) {
						wall = ImageFactory.getInstance().getMurVertical();

					} else if (!murDroite && murGauche && murHaut && murBas) {
						wall = ImageFactory.getInstance().getMurVertical();

					} else if (murDroite && !murGauche && !murHaut && murBas) {
						wall = ImageFactory.getInstance().getCoinHautGauche();

					} else if (!murDroite && murGauche && !murHaut && murBas) {
						wall = ImageFactory.getInstance().getCoinHautDroit();

					} else if (murDroite && !murGauche && murHaut && !murBas) {
						wall = ImageFactory.getInstance().getCoinBasGauche();

					} else if (!murDroite && murGauche && murHaut && !murBas) {
						wall = ImageFactory.getInstance().getCoinBasDroit();

					} else if (murDroite && murGauche && !murHaut && !murBas) {
						wall = ImageFactory.getInstance().getMurHorizontal();

					} else if (!murDroite && !murGauche && murHaut && murBas) {
						wall = ImageFactory.getInstance().getMurVertical();

					} else if (murDroite && murGauche && murHaut && murBas) {
						Case[] voisinVoisin = monde.getVoisins(new Position(voisin[0].getX(), voisin[0].getY()));
						voisinVoisin = monde.getVoisins(new Position(voisin[0].getX(), voisin[0].getY()));
						boolean voisinVoisinGaucheBas = voisinVoisin[3].isMur();
						boolean voisinVoisinGaucheHaut = voisinVoisin[1].isMur();
						voisinVoisin = monde.getVoisins(new Position(voisin[2].getX(), voisin[2].getY()));
						boolean voisinVoisinDroiteBas = voisinVoisin[3].isMur();
						voisinVoisin = monde.getVoisins(new Position(voisin[1].getX(), voisin[1].getY()));
						boolean voisinVoisinHautDroite = voisinVoisin[2].isMur();


						if (!voisinVoisinGaucheBas) {
							wall = ImageFactory.getInstance().getCoinHautDroit();

						} else if (!voisinVoisinDroiteBas) {
							wall = ImageFactory.getInstance().getCoinHautGauche();

						} else if (!voisinVoisinGaucheHaut) {
							wall = ImageFactory.getInstance().getCoinBasDroit();

						} else if (!voisinVoisinHautDroite) {
							wall = ImageFactory.getInstance().getCoinBasGauche();

						} else {
							wall = null;
						}


					} else {
						wall = null;
					}



				} else {
					wall = null;
				}
			}

			graphics2D.drawImage(wall,   colonne * 24,     ligne * 24, null);



		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void drawPacman(Monde monde, Graphics2D graphics2D) {
		BufferedImage sprite = monde.getPacman().getImage();
		BufferedImage imScale = resize(sprite, 20, 20);
		Pacman pacman = monde.getPacman();
		int posx = pacman.getPosition().getX();
		int posy = pacman.getPosition().getY();
		graphics2D.drawImage(imScale, posx * 24 + 102, posy * 24 + 12, null);

	}

	public void drawPiece(Monde monde, Graphics2D graphics2D) {
		Map<Position, Piece> pieces = monde.getLabyrinthe().getPieces();
		BufferedImage sprite;
		for (Position position : pieces.keySet()) {
			Piece piece = pieces.get(position);
			sprite = piece.getImage();
			int decalage = (24 - sprite.getWidth())/2;
			graphics2D.drawImage(piece.getImage(), position.getX() * 24 + 100 + decalage, position.getY() * 24 + 10 + decalage, null);
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
			this.vie = (Integer) evt.getNewValue();

		}
	}
}
