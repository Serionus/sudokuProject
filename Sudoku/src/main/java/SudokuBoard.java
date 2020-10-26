import java.util.Random;

public class SudokuBoard {
    private final int[][] board = new int[9][9];

    public int get(int x, int y){
        return board[x][y];
    }

    public void set(int x, int y, int value){
        board[x][y] = value;
    }

    public void solveGame(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(this);
    }

    /*
    private int generateSudokuFigures() {
        Random rand = new Random();
        return rand.nextInt(9) + 1;
    }

    public void randomFillBoard() {
        for (int i = 0; i < 9; i++) {
            int column = generateSudokuFigures() - 1;

            do {
                board[i][column] = generateSudokuFigures();
            } while (!viabilityTest(i, column));
        }
    }
    */



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

}
