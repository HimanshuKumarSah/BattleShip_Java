package BattleShip;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private final List<Ship> ships = new ArrayList<>();
    public Player(int boardSize) {
        placeShips(boardSize);
    }

    public List<Ship> getShips(){
        return ships;
    }

    private int random(int boardSize, int boatSize) {
        int random = ThreadLocalRandom.current().nextInt(boardSize);
        while (random + boatSize > boardSize){
            --random;
        }
        return random;
    }

    private List<Coordinate> getAllCoordinates(Coordinate start, Coordinate end){
        List<Coordinate> coordinates = new ArrayList<>();

        if(start.getX() == end.getX()){
            int min = Math.min(start.getY(), end.getY());
            int max = Math.max(start.getY(), end.getY());

            for (int y = min; y < max; ++y){
                coordinates.add(new Coordinate(start.getX(), y));
            }
        } else {
            int min = Math.min(start.getX(), end.getX());
            int max = Math.max(start.getX(), end.getX());

            for (int x = min; x < max; ++x){
                coordinates.add(new Coordinate(x, start.getY()));
            }
        }
        return  coordinates;
    }
    private boolean isInUse(Coordinate coordinate){
        for (Ship ship : ships){
            if (ship.inUse(coordinate)){
                return true;
            }
        }
        return false;
    }
    private void placeShips(int boardSize){
        for (int size: Ship.getSizes()){
            boolean vert = ThreadLocalRandom.current().nextBoolean();
            List<Coordinate> coordinates;

            main: while(true){
                int startX = random(boardSize, size);
                int startY = random(boardSize, size);
                Coordinate start = new Coordinate(startX, startY);

                int endX = vert ? startX : startX + size;
                int endY = vert ? startY + size : startY;

                Coordinate end = new Coordinate(endX, endY);

                coordinates = getAllCoordinates(start, end);
                for(Coordinate coordinate : coordinates){
                    if (isInUse(coordinate)){
                        continue main;
                    }
                }
                break;
            }
            ships.add(new Ship(coordinates));
        }
    }
}
