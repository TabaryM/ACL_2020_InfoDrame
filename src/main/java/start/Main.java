package start;

import engine.controller.MenuPauseController;
import engine.controller.MenuPrincipalController;
import engine.view.MenuPausePainter;
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
		//PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanGame game = new PacmanGame();
		PacmanPainter painter = new PacmanPainter();
		PacmanController controller = new PacmanController();
		MenuPrincipalController menuPrincipalController = new MenuPrincipalController();
		MenuPrincipalPainter menuPrincipalPainter = new MenuPrincipalPainter(menuPrincipalController);
		MenuPauseController menuPauseController = new MenuPauseController();
		MenuPausePainter menuPausePainter = new MenuPausePainter(menuPauseController);

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller, menuPrincipalPainter, menuPrincipalController, menuPausePainter, menuPauseController);
		engine.run();
	}

}
