package BattleShip;
public class Coordinate {
    private int x;
    private int y;

    private boolean hit = false;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public boolean isHit(){
        return hit;
    }
    public void setHit(boolean hit){
        this.hit=hit;
    }
    public String toString() {
        return x + " , " + y;
    }
}
