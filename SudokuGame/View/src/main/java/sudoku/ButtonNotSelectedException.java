package sudoku;

public class ButtonNotSelectedException extends Exception{
    public ButtonNotSelectedException(){
        super("Nie wybrano pola, wiec nie dziala");
    }
}
