package sudoku.boardelements;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.simple()
                .forClass(SudokuField.class)
                .withIgnoredFields("support")
                .verify();
    }

    @Test
    void testToString() {
        SudokuField testField = new SudokuField(null);
        assertTrue(testField.toString().length() != 0);
    }

    @Test
    void cloneTest(){
        SudokuField testField = new SudokuField(null);
        testField.setValue(8);
        SudokuField clonedField = testField.clone();
        assertTrue(testField.equals(clonedField));
        clonedField.setValue(0);
        assertFalse(testField.equals(clonedField));
    }

    @Test
    void compareToTest(){
        SudokuField testField = new SudokuField(null);
        testField.setValue(8);
        SudokuField clonedField = testField.clone();
        assertEquals(0, testField.compareTo(clonedField));
        testField.setValue(9);
        assertEquals(1, testField.compareTo(clonedField));
        testField.setValue(1);
        assertEquals(-1, testField.compareTo(clonedField));
        assertEquals(-1, testField.compareTo(null));
    }

}