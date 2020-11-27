import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class SudokuBoardTest {
    @Test
    void solveGame() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertEquals(testBoard.get(i, j), 0);
            }
        }
        testBoard.setWantCheck(true);
        assertTrue(testBoard.isCorrect());
        testBoard.solveGame();
        assertTrue(testBoard.isCorrect());
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertTrue(testBoard.get(i, j) < 10 && testBoard.get(i, j) > 0);
            }
        }
    }

    @Test
    void checkListener(){
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        assertTrue(testBoard.isCorrect());

        testBoard.setWantCheck(true);

        for(int i = 0 ; i < 9 ; i ++){
            testBoard.set(0, i, i+1);
        }
        assertTrue(testBoard.isCorrect());

        testBoard.set(0, 0, 2);
        assertFalse(testBoard.isCorrect());
    }

    @Test
    void equalsHashCodeTest() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoardOne = new SudokuBoard(testSolver);
        SudokuBoard testBoardTwo = new SudokuBoard(testSolver);
        testBoardOne.set(0,0,1);
        testBoardTwo.set(0,0,1);
        assertTrue(testBoardOne.equals(testBoardTwo));
        testBoardTwo.set(8,8,1);
        assertFalse(testBoardOne.equals(testBoardTwo));
        assertTrue(testBoardOne.getBox(0,0).equals(testBoardTwo.getBox(0,0)));
        assertFalse(testBoardOne.getBox(2,2).equals(testBoardTwo.getBox(2,2)));
    }
}