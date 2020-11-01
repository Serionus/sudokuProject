public class Sudoku {
    public static void main(String[] args) {

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        System.out.println(board.toString());
        board.solveGame();
        System.out.println(board.toString());

    }
}
