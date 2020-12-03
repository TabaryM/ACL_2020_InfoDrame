package engine;

import engine.controller.*;
import engine.view.GamePainter;
import engine.view.GraphicalInterface;
import engine.view.MenuUI;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	public static final int TIMESTEP = 100;

	private static double multiplier;

	public static int getTimestep(){
		return (int)(TIMESTEP * (multiplier));
	}

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	private MenuUI menuUI;

	private MenuController menuController;

	private MenuUI pauseUI;

	private MenuController pauseController;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController, MenuUI menuUI, MenuController menuController, MenuUI pauseUI, MenuController pauseController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.menuUI = menuUI;
		this.menuController = menuController;
		this.pauseUI = pauseUI;
		this.pauseController = pauseController;
		multiplier = 1;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// Creation de la fenêtre du menu principal
		menuUI.create();
		pauseUI.create();

		// Boucle d'affichage du menu principal
		while (!menuController.play()){
			menuUI.display();
			Thread.sleep(getTimestep());
		}

		// Suppression de la fenêtre du menu principal
		menuUI.erase();

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);
		this.gui.paintLaby(this.game.getMonde());

		// boucle de game
		while(true) {
			while (!this.game.isFinished()) {
				// demande controle utilisateur
				Cmd c = this.gameController.getCommand();

				if (c == Cmd.PAUSE){
					pauseController.changePlay();
					gameController.setCommand(Cmd.IDLE);
				}
				while (!pauseController.play()){
					pauseUI.display();
					Thread.sleep(getTimestep());
				}
				pauseUI.erase();
				// fait evoluer le game
				this.game.evolve(c);
				// affiche le game
				this.gui.paint(this.game.getMonde());
				// met en attente
				Thread.sleep(getTimestep());
			}
			multiplier -= multiplier * 0.1;
			game.setFinished(false);
		}
	}

}
