package sudoku.boardelements;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import sudoku.SudokuBoard;

import javax.persistence.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@Entity

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    @Id
    @GeneratedValue
    private int id;

    int value = 0;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

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

    @Override
    public SudokuField clone() {
        try {
            SudokuField result =  (SudokuField) super.clone();
            result.support = new PropertyChangeSupport(support);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(SudokuField  o) {
            if (o != null && this.value == o.getValue()) {
                return 0;
            } else if (o != null && this.value > o.getValue()) {
                return 1;
            } else {
                return -1;
            }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public PropertyChangeSupport getSupport() {
        return support;
    }

    public void setSupport(PropertyChangeSupport support) {
        this.support = support;
    }
}

