import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void writeAndRead() {
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        FileSudokuBoardDao testFileDaoOne = new FileSudokuBoardDao("pliczekElektryczek");
        FileSudokuBoardDao testFileDaoTwo = new FileSudokuBoardDao("puste");
        testBoard.solveGame();
        assertTrue(testBoard.isCorrect());
        testFileDaoOne.write(testBoard);
        SudokuBoard testBoard2 =  testFileDaoOne.read();
        assertTrue(testBoard.equals(testBoard2));
        testFileDaoTwo.write(testSolver);
        testFileDaoTwo.read();


    }
}