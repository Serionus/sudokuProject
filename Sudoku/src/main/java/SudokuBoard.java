public class SudokuBoard {
    private int board[][] = new int[9][9];

    public void fillBoard(){

    }

    private boolean checkColumn(int c){
        int value = 0;
        for (int i = 0 ; i < 9 ; i++) {
            value += board[i][c];
        }
        if(value==9){
            return true;
        }
        return false;
    }

    private boolean checkRow(int r){
        int value = 0;
        for (int i = 0 ; i < 9 ; i++) {
            value += board[r][i];
        }
        if(value==9){
            return true;
        }
        return false;
    }

    private boolean checkbox(int c, int r){
        int value = 0;
        for (int i = 0 ; i < 9 ; i++) {
            value += board[r][i];
        }
        if(value==9){
            return true;
        }
        return false;
    }
}

