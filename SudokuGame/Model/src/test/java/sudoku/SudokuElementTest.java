package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import sudoku.boardelements.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuElementTest {
    @Test
    void verifyTest() {
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
    public void equalsContract() {
        EqualsVerifier.simple().forClass(SudokuElement.class).verify();
    }

    @Test
    void toStringTest() {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        SudokuBox box = new SudokuBox(fields);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(null));
            fields.get(i).setValue(i + 1);
        }
        assertTrue(box.toString().length() != 0);
    }

    @Test
    void cloneTest(){
        List<SudokuField> testFields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            testFields.set(i, new SudokuField(null));
            testFields.get(i).setValue(i + 1);
        }
        SudokuBox testBox = new SudokuBox(testFields);
        SudokuBox clonedBox = (SudokuBox) testBox.clone();
        assertTrue(testBox.equals(clonedBox));
        testFields.get(0).setValue(5);
        testBox = new SudokuBox(testFields);
//        System.out.println(testBox.toString());
//        System.out.println(clonedBox.toString());
//        assertFalse(testBox.equals(clonedBox));

    }
}
