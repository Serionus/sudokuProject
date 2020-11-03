import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class BacktrackingSudokuSolverTest {

        @Test
        void fillBoard() {

            BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
            SudokuBoard testBoardOne = new SudokuBoard(testSolver);

            testBoardOne.solveGame();
            int [][] testCopyOne = new int [9][9];
            int [][] testCopyTwo = new int [9][9];
            assertTrue(testBoardOne.checkBoard());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    testCopyOne[i][j] = testBoardOne.get(i, j);
                }
            }
            testBoardOne.solveGame();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    testCopyTwo[i][j] = testBoardOne.get(i, j);
                }
            }
            assertTrue(boardsNotEqual(testCopyOne, testCopyTwo));
        }

        public boolean boardsNotEqual(int [][] boardOne, int [][] boardTwo) {
            int equalFigures = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (boardOne[i][j] == boardTwo[i][j]) {
                        equalFigures++;
                    }
                }
            }
            return equalFigures != 81;
        }

}

