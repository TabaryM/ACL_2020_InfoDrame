package engine.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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
	protected static final int HEIGHT = 720;
	private int score = 0;
	private int vie = 3;

	/**
	 * appelle constructeur parent
	 *
	 */
	public PacmanPainter() {
		super();
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D graphics2D = im.createGraphics();
		Color background = new Color(0, 0, 0);
		graphics2D.setColor(background);
		graphics2D.fillRect(0, 0, getWidth(), getHeight());
		drawFont(graphics2D);
		drawIcon(graphics2D);
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
