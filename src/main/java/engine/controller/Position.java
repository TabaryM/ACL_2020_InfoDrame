package engine.controller;

/**
 * @author Tabary
 */
public class Position {
    private Integer x;
    private Integer y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(){
        this.x = 0;
        this.y = 0;
    }

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
