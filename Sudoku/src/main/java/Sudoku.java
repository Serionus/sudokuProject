import java.util.*;
public class Sudoku {
    public static void main(String[] args){
        SudokuBoard board = new SudokuBoard();
        board.showBoard();
        board.fillBoard();
        System.out.println("");
        System.out.println("");
        board.showBoard();
    }
}
