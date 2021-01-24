package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.*;

import sudoku.boardelements.SudokuBox;
import sudoku.boardelements.SudokuColumn;
import sudoku.boardelements.SudokuField;
import sudoku.boardelements.SudokuRow;
import javax.persistence.*;

@Entity
public class SudokuBoard implements PropertyChangeListener, Serializable, Cloneable {

    @OneToMany
    private List<SudokuField> fields;

    private final SudokuSolver solver;
    private final Difficulty diff;
    private boolean correct = true;
    private boolean wantCheck = false;

    @Id
    private String name;

    public enum Difficulty {
        EASY(30),
        MEDIUM(40),
        HARD(50),
        VERY_HARD(60);
        int numberOfRemovedFields;

        Difficulty(int number) {
            numberOfRemovedFields = number;
        }

        public SudokuBoard removeFields(SudokuBoard board) {
            int counter = numberOfRemovedFields;
            Random rand = new Random();
            while (counter != 0) {
                int randRow = rand.nextInt(9);
                int randCol = rand.nextInt(9);
                if (board.fields.get(randRow*9 + randCol).getValue() == 0) {
                    continue;
                }
                board.fields.get(randRow*9 + randCol).setValue(0);
                counter--;
            }
            return board;
        }
    }

    public SudokuBoard(SudokuSolver solver, Difficulty diff) {
        this.solver = solver;
        generateFields();
        this.diff = diff;
    }

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        generateFields();
        diff = Difficulty.EASY;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (wantCheck) {
            correct = this.checkBoard();
        }
    }

    @Override
    public SudokuBoard clone() {
        try {
            SudokuBoard result = (SudokuBoard) super.clone();
            List<SudokuField> clonedFields = new ArrayList<>();
            for (int i = 0; i < 81; i++) {
                SudokuField cloned = new SudokuField(result);
                cloned.setValue(fields.get(i).getValue());
                clonedFields.add(cloned);
            }
            result.fields = clonedFields;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard board = (SudokuBoard) o;
        return Objects.equal(fields, board.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fields);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fields", fields)
                .toString();
    }

    public void setWantCheck(boolean wantCheck) {
        this.wantCheck = wantCheck;
    }

    public boolean isWantCheck() {
        return wantCheck;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Difficulty getDifficulty() {
        return diff;
    }

    public SudokuSolver getSolver() {
        return solver;
    }

    public int get(int x, int y) {
        return fields.get(x*9 + y).getValue();
    }

    public void set(int x, int y, int value) {
        fields.get(x * 9 + y).setValue(value);
    }

    public SudokuField getField(int x, int y) {
        return fields.get(x * 9 + y);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> chosenFields = Arrays.asList(new SudokuField[9]);

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                chosenFields.set(a * 3 + b, new SudokuField(this,
                        fields.get((x * 3 + a)*9 + (y * 3 + b)).getValue()));
            }
        }
        return new SudokuBox(chosenFields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> chosenFields = new ArrayList<>();
        for (int j = 0; j < 9; j++) {
            chosenFields.add(fields.get(j*9 + x));
        }
        return new SudokuColumn(chosenFields);
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> chosenFields = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            chosenFields.add(fields.get(y*9 + i));
        }
        return new SudokuRow(chosenFields);
    }

    public void solveGame() {
        emptyingBoard();
        randomFillBoard();
        solver.solve(this);
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void randomFillBoard() {
        Random rand = new Random();
        int randRow = rand.nextInt(9);
        for (int i = 0; i < 9; i++) {
            fields.get(randRow*9 + i).setValue(i + 1);
        }
        Collections.shuffle(fields);
    }

    private void emptyingBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields.get(i*9 + j).setValue(0);
            }
        }
    }

    private void generateFields() {
        fields = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            fields.add(new SudokuField(this));
        }
    }

    public Difficulty getDiff() {
        return diff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SudokuField> getFields() {
        return fields;
    }

    public void setFields(List<SudokuField> fields) {
        this.fields = fields;
    }
}
