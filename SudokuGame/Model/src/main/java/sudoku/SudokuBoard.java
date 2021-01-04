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

public class SudokuBoard implements PropertyChangeListener, Serializable, Cloneable {
    private List<List<SudokuField>> fields = Arrays.asList(new List[9]);
    private final SudokuSolver solver;
    private final Difficulty diff;
    private boolean correct = true;
    private boolean wantCheck = false;

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
                if (board.fields.get(randRow).get(randCol).getValue() == 0) {
                    continue;
                }
                board.fields.get(randRow).get(randCol).setValue(0);
                counter--;
            }
            return board;
        }
    }

    public SudokuBoard(SudokuSolver solver, Difficulty diff) {
        this.solver = solver;
        generateFields(fields);
        this.diff = diff;
    }

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        generateFields(fields);
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
            List<List<SudokuField>> clonedFields = Arrays.asList(new List[9]);
            generateFields(clonedFields);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    clonedFields.get(i).set(j, fields.get(i).get(j).clone());
                }
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

    public Difficulty getDiff() {
        return diff;
    }

    public SudokuSolver getSolver() {
        return solver;
    }

    public int get(int x, int y) {
        return fields.get(x).get(y).getValue();
    }

    public void set(int x, int y, int value) {
        fields.get(x).get(y).setValue(value);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> chosenFields = Arrays.asList(new SudokuField[9]);

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                chosenFields.set(a * 3 + b, new SudokuField(this,
                        fields.get(x * 3 + a).get(y * 3 + b).getValue()));
            }
        }
        return new SudokuBox(chosenFields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> chosenFields = Arrays.asList(new SudokuField[9]);
        for (int j = 0; j < 9; j++) {
            chosenFields.set(j, new SudokuField(this, fields.get(j).get(x).getValue()));
        }
        return new SudokuColumn(chosenFields);
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> chosenFields = fields.get(y);
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
            fields.get(randRow).get(i).setValue(i + 1);
        }
        Collections.shuffle(fields.get(randRow));
    }

    private void emptyingBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields.get(i).get(j).setValue(0);
            }
        }
    }

    private void generateFields(List<List<SudokuField>> fields) {
        for (int i = 0; i < 9; i++) {
            List<SudokuField> row = Arrays.asList(new SudokuField[9]);
            for (int j = 0; j < 9; j++) {
                SudokuField field = new SudokuField(this);
                row.set(j, field);
            }
            fields.set(i, row);
        }
    }
}
