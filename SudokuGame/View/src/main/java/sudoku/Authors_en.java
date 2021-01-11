package sudoku;

import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"nameOne", "Michael"},
                {"surnameOne", "Corleone"},
                {"nameTwo", "Antonio"},
                {"surnameTwo", "Banderas"}
        };
    }
}
