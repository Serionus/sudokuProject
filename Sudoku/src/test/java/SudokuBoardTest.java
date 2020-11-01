import static org.junit.jupiter.api.Assertions.*;
public class SudokuBoardTest {

    //zgodnie z (mamy nadzieję) konwencją nie testujemy
    //konstruktora,
    //getterów,
    //setterów,
    //metod prywatnych,
    //metody toString
    //ponieważ co do zasady nie zawierają one logiki, którą wartoby przetestować.
    //Dependency Injection mamy nadzieję również nie wymaga testowania.

    //do przetestowania zostaje zatem tylko metoda solveGame, jednak poprawność
    //algorytmu testowana jest już w testach SudokuSolver, tak więc sprawdzamy jedynie
    //czy wszystkie pola zostały wypełnione, a więc czy rozwiązanie jest kompletne.

    @org.junit.jupiter.api.Test
    void solveGame() {

        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertEquals(testBoard.get(i, j), 0);
            }
        }

        testBoard.solveGame();

        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertTrue(testBoard.get(i, j) < 10 && testBoard.get(i, j) > 0);
            }
        }
    }


}