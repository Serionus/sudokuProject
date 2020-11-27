package boardelements;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class SudokuElement {
    List<SudokuField> fields = Arrays.asList(new SudokuField[9]);

    public SudokuElement(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {

        List<Integer> memory = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            memory.add(i + 1);
        }

        outer:
        for (SudokuField field : fields) {
            for (int i = 0; i < memory.size(); i++) {
                if (field.getValue() == 0) {
                    continue outer;
                }

                if (field.getValue() == memory.get(i)) {
                    memory.remove(i);
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuElement that = (SudokuElement) o;
        return com.google.common.base.Objects.equal(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(fields);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fields", fields)
                .toString();
    }
}
