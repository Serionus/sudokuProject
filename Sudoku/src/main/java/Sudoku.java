public class Sudoku {
    public static void main(String[] args) {

        //metoda "main" jest zbędna i nieuwzględniona w zadaniu, jednak
        //zostawiliśmy ją w celu prostego wyświetlania wyników pracy,
        //jest to niekiedy przydatne do zwizualizowania problemów
        //i niemniej satysfakcjonujące

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        System.out.println(board.toString());
        board.solveGame();
        System.out.println(board.toString());

    }
}
