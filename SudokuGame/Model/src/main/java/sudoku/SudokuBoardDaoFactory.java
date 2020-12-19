package sudoku;

public class SudokuBoardDaoFactory {
    public static final Dao<SudokuBoard> createFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    private SudokuBoardDaoFactory() {

    }
}
