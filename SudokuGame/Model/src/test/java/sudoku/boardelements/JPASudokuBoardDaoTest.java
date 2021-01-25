package sudoku.boardelements;

import org.junit.jupiter.api.Test;
import sudoku.*;

import static org.junit.jupiter.api.Assertions.*;

class JPASudokuBoardDaoTest {

    @Test
    void writeAndReadTest() throws FileCreateException, WrongFileChosenException {
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoardOne = new SudokuBoard(testSolver);
        testBoardOne.solveGame();

        try (JPASudokuBoardDao testDao = new JPASudokuBoardDao("test")){
            testDao.write(testBoardOne);
            SudokuBoard testBoardTwo = testDao.read();
            assertEquals(testBoardOne, testBoardTwo);
            assertFalse(testBoardOne == testBoardTwo);
        }


//write exception test
        FileSudokuBoardDao wrongDao = new FileSudokuBoardDao("");
        assertThrows(FileCreateException.class, () -> wrongDao.write(testBoardOne));

// read exception test
        FileSudokuBoardDao wrongDaoTwo = new FileSudokuBoardDao("Empty");
        assertThrows(WrongFileChosenException.class, wrongDaoTwo::read);
    }

    @Test
    void factoryTest(){
        Dao testFactory = SudokuBoardDaoFactory.createJPADao("test");
    }

    @Test
    void finalizeTest(){
        Dao testFactory = SudokuBoardDaoFactory.createJPADao("test");
        System.gc();
    }
}