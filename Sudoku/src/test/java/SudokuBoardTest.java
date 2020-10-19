package pl.first.firstjava;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuBoardTest {
    public SudokuBoardTest() {

    }
    @Test
    public void testSudokuBoard(){
        SudokuBoard board = new SudokuBoard();
        board.randomFillBoard();
        for(int i = 0; i < 0; i+){
            for(int j = 0; j < 0; i++){
                assertEquals(board.viabilityTest(i, j));
            }
        }
    }
}
