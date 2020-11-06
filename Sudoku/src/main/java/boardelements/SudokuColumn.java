package boardelements;

public class SudokuColumn extends SudokuElement {
    public SudokuColumn(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public String toString() {
        String result = "";
        for (SudokuField field: fields) {
            result += field.getValue() + System.lineSeparator();
        }
        return result;
    }
}
