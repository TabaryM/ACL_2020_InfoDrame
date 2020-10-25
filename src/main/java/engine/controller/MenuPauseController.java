package engine.controller;

/**
 * @author Tabary
 */
public class MenuPauseController implements MenuController {

    private boolean play;

    public MenuPauseController(){
        play = false;
    }

    @Override
    public boolean play() {
        return play;
    }
}
