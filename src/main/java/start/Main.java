package start;

import engine.controller.MenuPrincipalController;
import engine.view.MenuPrincipalPainter;
import engine.view.PacmanPainter;
import engine.GameEngineGraphical;
import engine.controller.PacmanController;
import model.PacmanGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter();
		PacmanController controller = new PacmanController();
		MenuPrincipalPainter menuPrincipalPainter = new MenuPrincipalPainter();
		MenuPrincipalController menuPrincipalController = new MenuPrincipalController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller, menuPrincipalPainter, menuPrincipalController);
		engine.run();
	}

}
