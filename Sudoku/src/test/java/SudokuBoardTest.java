import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @org.junit.jupiter.api.Test
    void fillBoard() {
        SudokuBoard testBoardOne = new SudokuBoard();
        testBoardOne.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(testBoardOne.viabilityTest(i, j));
            }
        }
        SudokuBoard testBoardTwo = new SudokuBoard();
        assertTrue(testBoardOne.boardsNotEqual(testBoardOne, testBoardTwo));
    }
}
