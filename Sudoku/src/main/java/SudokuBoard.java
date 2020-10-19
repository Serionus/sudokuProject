public class SudokuBoard {
    private int board[][] = new int[9][9];

    public void fillBoard() {

        int k = 1;

        for(int i = 0; i < 9; i++) {

            for(int j = 0; j < 9; j++) {

            //sprawdź czy dane pole nalezy do poczatkowo wypelnionych

                while(true){

                    if(k > 9){
                        board[i][j]=0;

                        if(j==0){
                            k = board[i-1][8]+1;
                            i--;
                            j = 7;
                        }else {
                            k = board[i][j-1] + 1;
                            j=j-2;
                        }

                        break;
                    }

                    board[i][j] = k;
                    if(viablityTest(i, j)){

                        k = 1;
                        break;
                    }
                    k++;
                }
            }
        }
    }


  //  public getNextCell

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


