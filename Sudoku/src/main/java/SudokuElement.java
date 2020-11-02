import java.util.ArrayList;
import java.util.List;

abstract public class SudokuElement {
    SudokuField [] fields;

    public SudokuElement (SudokuField [] fields){
        this.fields = fields;
    }

    public boolean verify (){

        List<Integer> memory = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            memory.add(i + 1);
        }

        outer:
        for (SudokuField field : fields) {
            for (int i = 0; i < memory.size(); i++){
                if (field.getValue() == memory.get(i)){
                    memory.remove(i);
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }
}
