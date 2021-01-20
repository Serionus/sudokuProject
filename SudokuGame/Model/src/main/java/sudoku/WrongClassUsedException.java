package sudoku;

public class WrongClassUsedException extends Exception{
    public WrongClassUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}
