import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class SudokuBoardTest {
    @Test
    void solveGame() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        //System.out.println(testBoard.toString());

        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertEquals(testBoard.get(i, j), 0);
            }
        }
        assertTrue(testBoard.checkBoard());
        testBoard.solveGame();
        assertTrue(testBoard.checkBoard());
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertTrue(testBoard.get(i, j) < 10 && testBoard.get(i, j) > 0);
            }
        }
        //System.out.println(testBoard.toString());
    }

    @Test
    void checkBoard() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        for(int i = 0 ; i < 9 ; i ++){
            testBoard.set(i, 0, i+1);
        }
        assertTrue(testBoard.checkBoard());
        testBoard.set(0, 1, 1);
        assertFalse(testBoard.checkBoard());


        testBoard.emptyingBoard();
        for(int i = 0 ; i < 9 ; i ++){
            testBoard.set(0, i, i+1);
        }
        assertTrue(testBoard.checkBoard());
        testBoard.set(1, 0, 1);
        assertFalse(testBoard.checkBoard());


        testBoard.emptyingBoard();
        for(int i = 0 ; i < 3 ; i ++){
            for(int j = 0 ; j < 3 ; j ++){
                testBoard.set(i, j, 9-(i*3+j));
            }
        }


        assertTrue(testBoard.checkBoard());
        testBoard.set(1, 1, 1);
        assertFalse(testBoard.checkBoard());
    }

    @Test
    void checkListener(){
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        System.out.println(testBoard.toString());
        assertTrue(testBoard.isCorrect());
        testBoard.setWantCheck(true);
        for(int i = 0 ; i < 9 ; i ++){
            testBoard.set(0, i, i+1);
        }
        System.out.println(testBoard.toString());
        assertTrue(testBoard.isCorrect());

        testBoard.set(0, 0, 2);
        System.out.println(testBoard.toString());
        assertTrue(testBoard.isCorrect());
    }

}