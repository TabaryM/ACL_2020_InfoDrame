package engine.controller;

/**
 * @author Tabary
 */
public class MenuPauseController implements MenuController {

    private boolean play;

    public MenuPauseController(){
        play = true;
    }

    @Override
    public boolean play() {
        return play;
    }

    public void changePlay(){
        play = !play;
    }
}
