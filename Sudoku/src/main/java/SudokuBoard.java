import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {

    private final int[][] board = new int[9][9];
    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void solveGame() {
        emptyingBoard();
        randomFillBoard();
        solver.solve(this);
    }

    public void showBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("-------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    private int generateSudokuFigures() {
        Random rand = new Random();
        return rand.nextInt(9) + 1;
    }

    public void randomFillBoard() {
        int [] randomizedNumbers = new int [9];

        for (int i = 0; i < 9; i++) {
            int column = generateSudokuFigures() - 1;
            int value = generateSudokuFigures();
            while (searchUsedFiguresArray(randomizedNumbers, value)) {
                value = generateSudokuFigures();
            }
            board[i][column] = value;
            randomizedNumbers[i] = value;
        }
    }


    public boolean searchUsedFiguresArray(int [] array, int value) {
        for (int i = 0; i < 9; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void emptyingBoard() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }
}
