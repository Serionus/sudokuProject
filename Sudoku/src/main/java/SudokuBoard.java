import BoardElements.SudokuBox;
import BoardElements.SudokuColumn;
import BoardElements.SudokuField;
import BoardElements.SudokuRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuBoard {

    private final SudokuField[][] fields = new SudokuField[9][9];
    private final SudokuBox[][] boxes = new SudokuBox[3][3];
    private final SudokuColumn[] columns = new SudokuColumn[9];
    private final SudokuRow[] rows = new SudokuRow[9];
    SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        generateFields();
        generateBoxes();
        generateColumns();
        generateRows();
    }

    public int get(int x, int y) {
        return fields[x][y].getValue();
    }

    public void set(int x, int y, int value) {
        fields[x][y].setValue(value);
    }

    public SudokuBox getBox(int x, int y){
        return boxes[x][y];
    }

    public SudokuColumn getColumn(int x){
        return columns[x];
    }

    public SudokuRow getRow(int y){
        return rows[y];
    }

    public boolean checkBoard(){
        for(SudokuColumn column: columns){
            if(!column.verify()){
                return false;
            }
        }
        for(SudokuRow row: rows){
            if(!row.verify()){
                return false;
            }
        }
        for(SudokuBox[] boxes2: boxes){
            for(SudokuBox box: boxes2){
                if(!box.verify()){
                    return false;
                }
            }
        }
        return true;
    }

    public void solveGame() {
        emptyingBoard();
        randomFillBoard();
        solver.solve(this);
    }

    @Override
    public String toString() {

        String result = "";

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                result += "-------------------------" + System.lineSeparator();
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    result += "| ";
                }
                result += fields[i][j].getValue() + " ";
            }
            result += "|" + System.lineSeparator();
        }
        result += "-------------------------" + System.lineSeparator();
        return result;

    }
    private void randomFillBoard() {
        List<Integer> memory = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i < 10; i++) {
            memory.add(i);
        }
        for (int i = 0; i < 9; i++) {
            int randomNumber = rand.nextInt(memory.size());
            fields[i][rand.nextInt(9)].setValue(memory.get(randomNumber));
            memory.remove(randomNumber);
        }
    }
    private void emptyingBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9; j++) {
                fields[i][j].setValue(0);
            }
        }
    }

    private void generateFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuField field = new SudokuField();
                fields[i][j] = field;
            }
        }
    }
    private void generateColumns() {

        for (int i = 0; i < 9; i++) {
            SudokuField[] chosenFields = new SudokuField[9];
            for (int j = 0; j < 9; j++) {
                chosenFields[j] = fields[j][i];
            }
            columns[i] = new SudokuColumn(chosenFields);
        }
    }

    private void generateRows(){
        SudokuField[] chosenFields;

        for(int i = 0; i < 9; i++){
            chosenFields = fields[i];
            SudokuRow row = new SudokuRow(chosenFields);
            rows[i] = row;
        }
    }

    private void generateBoxes(){

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                SudokuField[] chosenFields = new SudokuField[9];
                for(int a = 0; a < 3; a++){
                    for(int b = 0 ;b < 3; b++){
                        chosenFields[a * 3 + b] = fields[i * 3 + a][j * 3 + b];
                    }
                }
                SudokuBox box = new SudokuBox(chosenFields);
                boxes[i][j] = box;
            }
        }
    }

}
