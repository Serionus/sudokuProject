import java.util.Random;

public class SudokuBoard {
    private final int[][] board = new int[9][9];

    public void fillBoard() {
        randomFillBoard();
        int[][] oldBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                oldBoard[i][j] = board[i][j];
            }
        }
        int k = 1;

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                if (oldBoard[i][j] == 0) {

                    while (true) {

                        if (k > 9) { //cofamy się o jedną kolumnę lub rząd kiedy wartość nie pasuje
                            board[i][j] = 0;

                            int[] params = getPreviousCell(i, j);

                            while (oldBoard[params[0]][params[1]] != 0) {
                                params = getPreviousCell(params[0], params[1]);
                            }

                            k = board[params[0]][params[1]] + 1;
                            i = params[0];
                            j = params[1] - 1;

                            break;
                        }

                        board[i][j] = k;
                        if (viabilityTest(i, j)) {
                            k = 1;
                            break;
                        }
                        k++;
                    }
                }
            }
        }
        showBoard();
    }

    private int[] getPreviousCell(int i, int j) {
        int[] result = new int[2];
        if (j == 0) {
            result[0] = i - 1;
            result[1] = 8;
        } else {
            result[0] = i;
            result[1] = j - 1;
        }
        return result;
    }

    public boolean viabilityTest(int row, int column) {

        for (int i = 0; i < 9; i++) {
            if (i == row) {
                continue;
            }
            if (board[row][column] == board[i][column]) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i == column) {
                continue;
            }
            if (board[row][column] == board[row][i]) {
                return false;
            }
        }

        int boxRow = row / 3;
        int boxCol = column / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((boxRow * 3) + i == row && (boxCol * 3) + j == column) {
                    continue;
                }
                if (board[(boxRow * 3) + i][(boxCol * 3) + j] == board[row][column]) {
                    return false;
                }
            }
        }

        return true;
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
        for (int i = 0; i < 9; i++) {
            int column = generateSudokuFigures() - 1;

            do {
                board[i][column] = generateSudokuFigures();
            } while (!viabilityTest(i, column));
        }

    }

    public int getBoardValue(int i, int j) {
        return board[i][j];
    }

    public boolean boardsNotEqual(SudokuBoard boardOne, SudokuBoard boardTwo) {
        int equalFigures = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardOne.getBoardValue(i, j) == boardTwo.getBoardValue(i, j)) {
                    equalFigures++;
                }
            }
        }
        return equalFigures != 81;
    }
}
