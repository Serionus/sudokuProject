import static org.junit.jupiter.api.Assertions.*;
public class BacktrackingSudokuSolverTest {

        @org.junit.jupiter.api.Test
        void fillBoard() {
            SudokuBoard testBoardOne = new SudokuBoard();
            BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
            testBoardOne.solveGame();
            int [][] testCopyOne = new int [9][9];
            int [][] testCopyTwo = new int [9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    assertTrue(testSolver.viabilityTest(i, j, testBoardOne));
                }
            }
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

