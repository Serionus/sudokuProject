import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SudokuBoard {

    private final int[][] board = new int[9][9];
    SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void solveGame() {
        emptyingBoard();
        randomFillBoard2();
        solver.solve(this);
    }

    @Override
    public String toString() {

        String result = "";

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                result += "-------------------------" + System.lineSeparator();
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    result += "| ";
                }
                result += board[i][j] + " ";
            }
            result += "|" + System.lineSeparator();
        }
        result += "-------------------------" + System.lineSeparator();
        return result;

    }

    //nazwa zwyczajowa, wzięta z czasów kiedy to kolega Michał napisał pierwotną
    //wersję metody randomfillboard, w sposób optymalny pod wzlędem ilości spędzonego
    //przy kodowaniu czasu, ale niestety nie do końca optymalnym pod każdym innym względem.
    //ku pamięci metoda ta została zapamiętana w komentarzu na końcu pliku. :)
    private void randomFillBoard2() {
        List<Integer> memory = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 1; i < 10; i++) {
            memory.add(i);
        }
        for (int i = 0; i < 9; i++) {
            int randomNumber = rand.nextInt(memory.size());
            board[i][rand.nextInt(9)] = memory.get(randomNumber);
            memory.remove(randomNumber);
        }
    }

    private void emptyingBoard() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    /*
         private int generateSudokuFigures() {
        Random rand = new Random();
        return rand.nextInt(9) + 1;
    }

    private void randomFillBoard() {
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

    private boolean searchUsedFiguresArray(int [] array, int value) {
        for (int i = 0; i < 9; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
    */
}
