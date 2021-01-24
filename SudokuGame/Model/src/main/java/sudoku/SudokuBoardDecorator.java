package sudoku;

public abstract class SudokuBoardDecorator extends SudokuBoard {
    private SudokuBoard board;


    public SudokuBoardDecorator(SudokuBoard board) {
        super(board.getSolver(), board.getDifficulty());
        this.board = board;
    }




}
