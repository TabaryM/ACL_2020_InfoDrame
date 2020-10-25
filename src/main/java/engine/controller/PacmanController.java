package engine.controller;

import java.awt.event.KeyEvent;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}


	public void setCommand(Cmd cmd) {
		commandeEnCours = cmd;
	}

	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyChar()) {
		// si on appuie sur 'q',commande joueur est gauche
		case 'l':
		case 'L':
			this.commandeEnCours = Cmd.LEFT;
			break;
		case 'p':
		case 'P':
			this.commandeEnCours = Cmd.PAUSE;
			break;
		}

	}

	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		//this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * ne fait rien
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
