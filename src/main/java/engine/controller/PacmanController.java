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

	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Jeu raciste, on ne joue qu'avec un clavier Fronçais
		switch (e.getKeyChar()) {
			case 'q':
			case 'Q':
				this.commandeEnCours = Cmd.LEFT;
				break;
			case 'z':
			case 'Z':
				this.commandeEnCours = Cmd.UP;
				break;
			case 's':
			case 'S':
				this.commandeEnCours = Cmd.DOWN;
				break;
			case 'd':
			case 'D':
				this.commandeEnCours = Cmd.RIGHT;
				break;
			default:
				//System.out.println(e.getKeyChar()+"\t"+e.getKeyCode()+"\t"+e.getKeyLocation());
		}
	}

	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * ne fait rien
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
