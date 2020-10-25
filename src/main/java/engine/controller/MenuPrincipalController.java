package engine.controller;

import engine.view.GraphicalInterface;

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

    public void changePlay(){
        play = !play;
    }
}
