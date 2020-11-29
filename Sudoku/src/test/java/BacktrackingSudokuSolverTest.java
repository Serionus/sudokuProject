import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BacktrackingSudokuSolverTest {

        @Test
        void fillBoardTest() {

            BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
            SudokuBoard testBoardOne = new SudokuBoard(testSolver);
            testBoardOne.setWantCheck(true);

            testBoardOne.solveGame();



            assertTrue(testBoardOne.isCorrect());

            SudokuBoard testCopyOne = new SudokuBoard(testSolver);
            SudokuBoard testCopyTwo = new SudokuBoard(testSolver);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    testCopyOne.set(i, j, testBoardOne.get(i, j));
                }
            }

            testBoardOne.solveGame();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    testCopyTwo.set(i, j, testBoardOne.get(i, j));
                }
            }
            assertFalse(testCopyOne.equals(testCopyTwo));
        }
}

