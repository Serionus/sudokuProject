import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SudokuRowTest {
    @Test
    void verify() {
        SudokuField [] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++){
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields[i] = field;
        }
        SudokuRow testRow = new SudokuRow(fields);
        assertTrue(testRow.verify());
        SudokuField pom;
        pom = fields[1];
        fields[1] = fields[0];
        fields[0] = pom;
        for (int i = 0; i < 9; i++){
            System.out.println(fields[i].getValue());
        }
        assertTrue(testRow.verify());
        fields[3].setValue(0);
        assertTrue(!testRow.verify());
    }
}
