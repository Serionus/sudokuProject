import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void writeAndReadTest() throws Exception {
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        testBoard.solveGame();
        try(Dao<SudokuBoard> testDaoOne = SudokuBoardDaoFactory.createFileDao("testPlik")){
            testDaoOne.write(testBoard);
            SudokuBoard testBoard2 = testDaoOne.read();
            assertTrue(testBoard2.equals(testBoard));
        }
        try(Dao<SudokuBoard> testDaoTwo = SudokuBoardDaoFactory.createFileDao("")){
            testDaoTwo.write(testBoard);
            SudokuBoard testBoard3 = testDaoTwo.read();
        } catch (IOException e){
            System.out.println("Tak ma być");
        }
    }

    @Test
    void factoryTest(){
        Dao testFactory = SudokuBoardDaoFactory.createFileDao("test");
    }

    @Test
    void finalizeTest(){
        Dao testFactory = SudokuBoardDaoFactory.createFileDao("test");
        System.gc();
    }
}