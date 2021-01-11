package sudoku;

import java.util.ArrayList;
import java.util.List;

public abstract class SudokuBoardDecorator extends SudokuBoard {
    private SudokuBoard board;

    public SudokuBoardDecorator(SudokuBoard board) {
        super(board.getSolver(), board.getDiff());
        this.board = board;
    }




}
