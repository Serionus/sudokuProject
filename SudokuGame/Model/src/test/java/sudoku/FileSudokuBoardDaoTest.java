package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

//    public interface dzialanieDwuargumentowe{
//        public int wykonajDzialanie(int x, int y);
//    }
//
//    public interface warunekPoprawnosci{
//        public boolean sprawdzPoprawnosc(int x, int y);
//    }
//
//    private int dzialaniePodstawowe(int x, int y, dzialanieDwuargumentowe dzialanie){
//        return dzialanie.wykonajDzialanie(x, y);
//    }
//
//    private boolean zweryfikujPoprawnosc(int x, int y, warunekPoprawnosci warunek){
//        return warunek.sprawdzPoprawnosc(x, y);
//    }

    @Test
    void writeAndReadTest() throws FileCreateException, WrongFileChosenException{
//        System.out.println(zweryfikujPoprawnosc(16, 2, (int x, int y)-> x==y));
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoardOne = new SudokuBoard(testSolver);
        testBoardOne.solveGame();
        FileSudokuBoardDao testDao = new FileSudokuBoardDao("TestSave");

        testDao.write(testBoardOne);
        SudokuBoard testBoardTwo = testDao.read();
        assertTrue(testBoardTwo.equals(testBoardOne));

//write exception test
        FileSudokuBoardDao wrongDao = new FileSudokuBoardDao("");
        assertThrows(FileCreateException.class, () -> wrongDao.write(testBoardOne));

// read exception test
        FileSudokuBoardDao wrongDaoTwo = new FileSudokuBoardDao("Empty");
        assertThrows(WrongFileChosenException.class, wrongDaoTwo::read);
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