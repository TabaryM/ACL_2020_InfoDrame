package engine.controller;

import java.awt.event.KeyEvent;

/**
 * @author Tabary
 */
public class MenuPrincipalController implements MenuController {

    private boolean play;

    public MenuPrincipalController(){
        play = false;
    }

    public boolean play(){
        return play;
    }
}
