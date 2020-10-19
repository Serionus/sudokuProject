public class SudokuBoard {
    private int board[][] = new int[9][9];

    public void fillBoard() {

        int k = 1;

        for(int i = 0; i < 2; i++) {

            for(int j = 0; j < 9; j++) {



                while(true){
                    board[i][j] = k;
                    if(viablityTest(i, j)){
                        k = 1;
                        break;
                    }

                    if(k == 10){

                        if(j>8){
                            k = board[i-1][8]+1;
                            i--;
                            j = 7;
                        }else {
                            k = board[i][j] + 1;
                            j=j-2;
                        }

                        break;
                    }

                    k++;
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
                if ((boxRow * 3)+i == row && (boxCol * 3) + j == column) {
                    continue;
                }
                if (board[(boxRow * 3) + i][(boxCol * 3) + j] == board[row][column]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void showBoard(){
        for(int i = 0; i < 9 ; i++){
            if(i%3==0){
                System.out.println("-------------------------");
            }
            for(int j = 0 ; j < 9 ; j++){
                if(j%3 == 0){
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("-------------------------");
    }


}


