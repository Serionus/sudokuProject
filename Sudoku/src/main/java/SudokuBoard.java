public class SudokuBoard {
    private int board[][] = new int[9][9];

    public void fillBoard() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                for(int k = 1; k < 10; k++) {
                    board[i][j] = k;
                    if(viablityTest(i, j)){
                        break;
                    }
                }
            }
        }
    }

    private boolean viablityTest(int row, int column) {

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
                if (i == row && j == column) {
                    continue;
                }
                if (board[(boxRow * 3) + i][(boxCol * 3) + j] == board[row][column]) {
                    return false;
                }
            }
        }

        return true;
    }

}


