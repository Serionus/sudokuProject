package sudoku;

import java.util.ResourceBundle;

public class SudokuBoardDaoFactory {
    public static Dao<SudokuBoard> createFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> createFileDao(String fileName, ResourceBundle bundle) {
        return new FileSudokuBoardDao(fileName, bundle);
    }

    private SudokuBoardDaoFactory() {

    }
}
