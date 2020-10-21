package engine.controller;

/**
 * @author Tabary
 */
public class Position {
    private Integer x = 0;
    private Integer y = 0;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void moveLeft(){
        x--;
    }

    public void moveRight(){
        x++;
    }

    public void moveUp(){
        y--;
    }

    public void moveDown(){
        y++;
    }

    @Override
    public String toString(){
        return "(x="+x+"  y="+y+")";
    }
}
