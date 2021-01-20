package sudoku;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SudokuBoardTest {
    @Test
    void solveGameTest() {
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
    void listenerTest() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        assertTrue(testBoard.isCorrect());

        testBoard.setWantCheck(true);
        assertTrue(testBoard.isWantCheck());

        for (int i = 0; i < 9; i++) {
            testBoard.set(0, i, i + 1);
        }
        assertTrue(testBoard.isCorrect());

        testBoard.set(0, 0, 2);
        assertFalse(testBoard.isCorrect());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple()
                .forClass(SudokuBoard.class).withPrefabValues(SudokuSolver.class,
                new BacktrackingSudokuSolver(), new BacktrackingSudokuSolver())
                .withIgnoredFields("solver")
                .withIgnoredFields("correct")
                .withIgnoredFields("wantCheck")
                .withIgnoredFields("diff")
                .verify();
    }

    @Test
    void toStringTest() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoardOne = new SudokuBoard(testSolver);
        assertTrue(testBoardOne.toString().length() != 0);
    }

    @Test
    void cloneTest(){
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);
        testBoard.solveGame();
        SudokuBoard clonedBoard = testBoard.clone();
        assertTrue(clonedBoard.equals(testBoard));
        clonedBoard.set(0,0,0);
        assertFalse(clonedBoard.equals(testBoard));
    }

    @Test
    void difficultyTest(){
        BacktrackingSudokuSolver testSoler = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSoler, SudokuBoard.Difficulty.EASY);
        testBoard.solveGame();
        testBoard.getDifficulty().removeFields(testBoard);
        assertEquals(30, emptyFields(testBoard));
    }

    public int emptyFields(SudokuBoard sb){
        int counter = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (sb.get(i, j) == 0){
                    counter++;
                }
            }
        }
        return counter;

    }
}