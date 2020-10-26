public class Sudoku {
    public static void main(String[] args) {

        SudokuBoard board = new SudokuBoard();
        board.showBoard();
        board.randomFillBoard();
        board.showBoard();
        board.solveGame();
        board.showBoard();

    }
}
