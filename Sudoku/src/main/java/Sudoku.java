import java.util.*;
public class Sudoku {
    public static void main(String[] args){

        SudokuBoard board = new SudokuBoard();

        board.showBoard();

        System.out.println("");
        System.out.println("");

        board.randomFillBoard();
        board.showBoard();

        System.out.println("");
        System.out.println("");

        board.fillBoard();
        board.showBoard();
    }
}
