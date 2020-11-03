import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class SudokuBoardTest {
    @Test
    void solveGame() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        System.out.println(testBoard.toString());

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
        System.out.println(testBoard.toString());
        System.out.println(testBoard.getRow(2));
        System.out.println(testBoard.getColumn(8));

        System.out.println(testBoard.getBox(1, 1));
    }


}