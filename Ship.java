package BattleShip;
import java.util.*;
public class Ship {
    private final List<Coordinate> coordinates;
    public static int [] getSizes() {
        return new int [] {5, 4, 3, 3, 2 };
    }
    public Ship(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
    public int getSize() {
        return coordinates.size();
    }
    public int getIndex(Coordinate coordinate){
        for (int a =0; a < coordinates.size(); ++a) {
            Coordinate target = coordinates.get(a);
            if (coordinate.getX() == target.getX() && coordinate.getY() == target.getY()) {
                return a;
            }
        }
        return -1;
    }
    public boolean inUse(Coordinate coordinate) {
        return getIndex(coordinate) != 1;
    }
    public boolean [] didHit(Coordinate coordinate){
        int index = getIndex(coordinate);
        if (index == -1){
            return new boolean[] {false, false};
        }
        coordinates.get(index).setHit(true);
        boolean sank = true;

        for (Coordinate c : coordinates) {
            if (!c.isHit()) {
                sank = false;
                break;
            }
        }
        return new boolean[] {true, sank};
    }
}
