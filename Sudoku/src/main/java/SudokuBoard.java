import java.util.Random;

public class SudokuBoard {
    private final int[][] board = new int[9][9];

    public void fillBoard() {
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

                        if (k > 9) {   // jeżeli do danego pola nie pasuje żadna liczba cofamy się do poprzedniego pola zwiększając jego wartość o jeden
                            board[i][j] = 0;




                            int[] params = getLastCell(i, j);

                            while (oldBoard[params[0]][params[1]] != 0){
                                params = getLastCell(params[0], params[1]);
                            }

                            k = board[params[0]][params[1]] + 1;
                            i = params[0];
                            j = params[1] - 1;

                            break;
                        }

                        board[i][j] = k;
                        if (viablityTest(i, j)) {
                            k = 1;
                            break;
                        }
                        k++;
                    }
                }
            }
        }
    }

    private int[] getLastCell(int i, int j) {
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
        int figure = 0;
        figure = rand.nextInt(9) + 1;
        return figure;
    }

    public void randomFillBoard(){
        int column = 0;

        for(int i = 0; i < 9; i++){
            column = generateSudokuFigures()-1;

            do {
                board[i][column] = generateSudokuFigures();
            } while(!viablityTest(i, column));
        }

    }
}

