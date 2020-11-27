package boardelements;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SudokuField {
    int value = 0;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SudokuField(PropertyChangeListener pcl) {
        addPropertyChangeListener(pcl);
    }

    public SudokuField(PropertyChangeListener pcl, int value) {
        addPropertyChangeListener(pcl);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        int pom = this.value;
        this.value = value;
        support.firePropertyChange("value", pom, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}

