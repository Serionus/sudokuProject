import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(SudokuBoard board) {
        int[][] oldBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                oldBoard[i][j] = board.get(i, j);
            }
        }
        int k = 1;

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                if (oldBoard[i][j] == 0) {

                    while (true) {

                        if (k > 9) { //cofamy się o jedną kolumnę lub rząd kiedy wartość nie pasuje
                            board.set(i, j, 0);

                            int[] params = getPreviousCell(i, j);

                            while (oldBoard[params[0]][params[1]] != 0) {
                                params = getPreviousCell(params[0], params[1]);
                            }

                            k = board.get(params[0], params[1]) + 1;
                            i = params[0];
                            j = params[1] - 1;

                            break;
                        }

                        board.set(i, j, k);
                        if (viabilityTest(i, j, board)) {
                            k = 1;
                            break;
                        }
                        k++;
                    }
                }
            }
        }
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

    public boolean viabilityTest(int row, int column, SudokuBoard board) {

        for (int i = 0; i < 9; i++) {
            if (i == row) {
                continue;
            }
            if (board.get(row, column) == board.get(i, column)) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i == column) {
                continue;
            }
            if (board.get(row, column) == board.get(row, i)) {
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
                if (board.get((boxRow * 3) + i, (boxCol * 3) + j) == board.get(row, column)) {
                    return false;
                }
            }
        }

        return true;
    }
}
