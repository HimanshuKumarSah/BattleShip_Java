package BattleShip;
import java.util.Arrays;
import java.util.*;

public class Board {
    private final int boardSize;
    private final char [][] board;
    private Arrays Array;
    private final List<Character> letters = Array.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
    private final Player player;

    public Board(int boardSize, Player player) {
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        this.player = player;
        createGameBoard();
        placeShips();
    }
    private void createGameBoard(){
        for (char [] row : board) {
            Arrays.fill(row, ' ');
        }
    }
    private void placeShips(){
        for (Ship ship : player.getShips()) {
            for (Coordinate coordinate : ship.getCoordinates()) {
                int x = coordinate.getX();
                int y = coordinate.getY();

                board[x][y] = String.valueOf(ship.getSize()).charAt(0);
            }
        }
    }
    public int getLetterIndex(char letter) {
        return letters.indexOf(letter);
    }
    public String attemptHit(int x, int y){
        Coordinate coordinate = new Coordinate(x,y);

        for (Ship ship : player.getShips()) {
            boolean [] hitData = ship.didHit(coordinate);
            boolean didHit = hitData[0];
            boolean sank  = hitData[1];

            if (didHit) {
                board[x][y] = 'X';
                return sank ? "You sank their battleship!" : "Hit";
            }
        }
        return "Miss!";
    }

    public String toString() {
        StringBuilder result = new StringBuilder("    ");
        String delimiter = " | ";
        for (int a = 0; a < boardSize; ++a) {
            result.append(letters.get(a));

            if (a < boardSize - 1){
                result.append(delimiter);
            }
        }
        result.append("\n");

        for (int x = 0; x < boardSize; ++x) {
            for (int y = 0; y < boardSize; ++y) {
                if (y == 0) {
                    result.append(x);
                    result.append(delimiter);
                }
                result.append(board[x][y]);
                result.append(delimiter);
            }
            result.append("\n");
        }
        return result.toString();
    }
}
