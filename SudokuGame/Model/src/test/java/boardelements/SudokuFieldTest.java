package boardelements;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

}