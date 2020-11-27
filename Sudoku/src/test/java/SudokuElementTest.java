import static org.junit.jupiter.api.Assertions.*;

import boardelements.SudokuColumn;
import boardelements.SudokuElement;
import boardelements.SudokuField;
import boardelements.SudokuRow;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuElementTest {
    @Test
    void verify() {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++){
            fields.set(i, new SudokuField(null));
            fields.get(i).setValue(i + 1);
        }

        SudokuRow testRow = new SudokuRow(fields);

        SudokuColumn testColumn = new SudokuColumn(fields);

        assertTrue(testRow.verify()); // 1 2 3 4 5 6 7 8 9

        int pom = fields.get(1).getValue();
        fields.get(1).setValue(fields.get(0).getValue());
        fields.get(0).setValue(pom);

        assertTrue(testRow.verify()); // 2 1 3 4 5 6 7 8 9

        assertTrue(testColumn.verify());
        fields.get(3).setValue(1);
        assertFalse(testRow.verify()); // 2 1 3 1 5 6 7 8 9
        assertFalse(testColumn.verify());
    }

    @Test
    void equalsHashCodeTest() {
        List<SudokuField> testFieldsOne = Arrays.asList(new SudokuField[9]);
        List<SudokuField> testFieldsTwo = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++){
            testFieldsOne.set(i, new SudokuField(null));
            testFieldsOne.get(i).setValue(i + 1);
            testFieldsTwo.set(i, new SudokuField(null));
            testFieldsTwo.get(i).setValue(i + 1);
        }
        assertTrue(testFieldsOne.equals(testFieldsTwo));
        testFieldsOne.get(1).setValue(0);
        assertFalse(testFieldsOne.equals(testFieldsTwo));


    }
}
