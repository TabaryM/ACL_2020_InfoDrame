package model;

import engine.controller.Position;

/**
 * @author Tabary
 */
public abstract class Piece {

    protected int x;
    protected int y;

    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
