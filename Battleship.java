package BattleShip;

import java.util.Scanner;
public class Battleship {
    public static void main(String [] args) {
        int boardSize = 10;
        Player player = new Player(boardSize);
        Board board = new Board(boardSize, player);

        runGameLoop(board);
    }
    private static void runGameLoop(Board board){
        Scanner scanner = new Scanner(System.in);
        String lastAction = null;

        while(true) {
            System.out.println(board);
            if (lastAction != null) {
                System.out.println(lastAction);
            }
            String coordinate;

            do {
                System.out.println("Enter a coordinate (ex \"A0\" or \"quit\" to stop playing.");

                coordinate = scanner.next().toUpperCase();
                if (coordinate.equalsIgnoreCase("quit")) {
                    return;
                }
            }   while (!isValidCoordinate(coordinate, board));
            char [] array = coordinate.toCharArray();
            int column  = board.getLetterIndex(array[0]);
            int row = array[1] - 48;

            lastAction = board.attemptHit(row, column);
        }
    }
    private static boolean isValidCoordinate(String coordinate, Board board){
        if (coordinate.length() != 2) {
            return false;
        }
        char [] array = coordinate.toCharArray();
        if (board.getLetterIndex(array[0]) == -1) {
            return false;
        }
        int value = array[1];
        return  value >= 48 && value <= 57;
    }
}
