package sudoku;


import javax.persistence.Entity;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(SudokuBoard board) {
        SudokuBoard oldBoard = new SudokuBoard(this);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                oldBoard.set(i, j, board.get(i, j));
            }
        }

        int k = 1;

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                if (oldBoard.get(i, j) == 0) {

                    while (true) {

                        if (k > 9) { //cofamy się o jedną kolumnę lub rząd kiedy wartość nie pasuje
                            board.set(i, j, 0);

                            int[] params = getPreviousCell(i, j);

                            while (oldBoard.get(params[0],params[1]) != 0) {
                                params = getPreviousCell(params[0], params[1]);
                            }

                            k = board.get(params[0], params[1]) + 1;
                            i = params[0];
                            j = params[1] - 1;

                            break;
                        }

                        board.set(i, j, k);
                        if (board.getRow(i).verify() && board.getColumn(j).verify()
                                && board.getBox(i / 3, j / 3).verify()
                                 ) {
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


}
