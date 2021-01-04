package sudoku;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SecondaryController {

    Scene scene;
    Button activeField;
    BacktrackingSudokuSolver solver;
    SudokuBoard sudokuBoard;

    public void initData(SudokuBoard.Difficulty difficulty){
        scene = App.getScene();
        activeField = (Button) scene.lookup("#f00");
        solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solver, difficulty);
        sudokuBoard.solveGame();
        SudokuBoard savedBoard = sudokuBoard;
        sudokuBoard = savedBoard.clone();
        sudokuBoard = sudokuBoard.getDiff().removeFields(sudokuBoard);
        readFromBoard();
    }

    @FXML
    private void fieldAction(ActionEvent event) {
        activeField = (Button) event.getSource();

    }

    @FXML
    private void setField(ActionEvent event){
        Button thisField = (Button) event.getSource();
        activeField.setText(thisField.getText());
    }


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot(App.getPrimaryLoader());
    }

    private void readFromBoard(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Button button = (Button) scene.lookup("#f" + i + j);
                button.setText(Integer.toString(sudokuBoard.get(i, j)));
                if(sudokuBoard.get(i,j)==0){
                    button.setText("");
                }
            }
        }
    }
}