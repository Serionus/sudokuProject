import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SudokuElementTest {
    @Test
    void verify() {
        SudokuField[] fields = new SudokuField[9];

        for (int i = 0; i < 9; i++){
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields[i] = field;
        }

        SudokuRow testRow = new SudokuRow(fields);

        assertTrue(testRow.verify()); // 1 2 3 4 5 6 7 8 9

        SudokuField pom = fields[1];
        fields[1] = fields[0];
        fields[0] = pom;

        assertTrue(testRow.verify()); // 2 1 3 4 5 6 7 8 9
        fields[3].setValue(1);
        assertFalse(testRow.verify()); // 2 1 3 1 5 6 7 8 9
        fields[3].setValue(0);
        assertFalse(testRow.verify()); // 2 1 3 0 5 6 7 8 9
    }
}
