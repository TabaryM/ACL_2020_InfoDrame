package engine.view;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 1080;
	protected static final int HEIGHT = 720;

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
	}

	public void drawFont(Graphics2D graphics2D) {
		Color font = new Color(255, 255, 255);
		graphics2D.setColor(font);
		graphics2D.setFont(graphics2D.getFont().deriveFont(25f));
		graphics2D.drawString("Score: 9000", 900, 50);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
