package sudoku;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"nameOne", "Michał"},
                {"surnameOne", "Gebel"},
                {"nameTwo", "Antoni"},
                {"surnameTwo", "Karwowski"}
        };
    }
}
