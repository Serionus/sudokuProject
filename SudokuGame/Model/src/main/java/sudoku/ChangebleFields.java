package sudoku;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ChangebleFields extends SudokuBoardDecorator {
    private final List<int[]> changeableFields = new ArrayList<>();


    public ChangebleFields(SudokuBoard board) {
        super(board);
    }

    public void markChangeableFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (get(i, j) == 0) {
                    changeableFields.add(new int[]{i, j});
                }
            }
        }
    }

    public boolean isChangeable(int x, int y) {
        for (int[] params : changeableFields) {
            if (params[0] == x && params[1] == y) {
                return true;
            }
        }
        return false;
    }
}
