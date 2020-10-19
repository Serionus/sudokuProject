import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @org.junit.jupiter.api.Test
        void fillBoard() {
        SudokuBoard board = new SudokuBoard();
        board.randomFillBoard();
        for(int i = 0; i < 0; i++){
            for(int j = 0; j < 0; i++){
                assertEquals(viabilityTest(i, j), true );
            }
        }
    }

    @org.junit.jupiter.api.Test
    void viablityTest() {
    }

    @org.junit.jupiter.api.Test
    void randomFillBoard() {
    }
}