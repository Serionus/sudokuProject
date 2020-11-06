package boardelements;

public class SudokuBox extends SudokuElement {
    public SudokuBox(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += fields[i * 3 + j].getValue() + " ";
            }
            result += System.lineSeparator();
        }
        return result;
    }
}
