package BoardElements;

import BoardElements.SudokuElement;

public class SudokuRow extends SudokuElement {
    public SudokuRow(SudokuField[] fields) {
        super(fields);
    }
    @Override
    public String toString(){
        String result = "";
        for(SudokuField field: fields){
            result += field.getValue() + " ";
        }
        return result;
    }
}
