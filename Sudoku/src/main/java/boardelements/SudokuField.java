package boardelements;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SudokuField  {
    int value = 0;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    public SudokuField(PropertyChangeListener pcl) {
        addPropertyChangeListener(pcl);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        support.firePropertyChange("value", this.value, value);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
