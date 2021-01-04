package sudoku;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class SecondaryController {

    Scene scene;
    Button activeField;
    BacktrackingSudokuSolver solver;
    SudokuBoard sudokuBoard;
    FileSudokuBoardDao dao;

    @FXML
    FileChooser fileChooser = new FileChooser();

    public void initData(SudokuBoard.Difficulty difficulty){
        scene = App.getScene();
        solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoardDecorator(solver, difficulty);
        sudokuBoard.solveGame();
        sudokuBoard = sudokuBoard.getDiff().removeFields(sudokuBoard);
        ((SudokuBoardDecorator)sudokuBoard).markFieldsAsChangeable();
        readFromBoard();
    }

    @FXML
    private void fieldAction(ActionEvent event) {
        activeField = (Button) event.getSource();
    }

    @FXML
    private void setField(ActionEvent event){
        Button thisField = (Button) event.getSource();
        int x = Character.getNumericValue(activeField.getId().charAt(1));
        int y = Character.getNumericValue(activeField.getId().charAt(2));
        if(thisField.getText().equals("")){
            sudokuBoard.set(x, y, 0);
        }else {
            sudokuBoard.set(x, y, Integer.parseInt(thisField.getText()));
        }
        readFromBoard();
    }

    @FXML
    private void markAsUncertain(ActionEvent event){
        if(activeField.getStyle().equals("-fx-background-color:#7FFFD4;")){
            activeField.setStyle(null);
        }else{
            activeField.setStyle("-fx-background-color:#7FFFD4;");
        }
    }

    @FXML
    private void save(ActionEvent event) throws IOException{
        TextField newSave = (TextField) scene.lookup("#saveName");
        dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.createFileDao(newSave.getText());
        dao.write(sudokuBoard);
    }

    @FXML
    private void load(ActionEvent event) throws ClassNotFoundException, IOException{
        fileChooser.setTitle("Wybierz zapis");
        File selectedDirectory = fileChooser.showOpenDialog(scene.getWindow());
        dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.createFileDao(selectedDirectory.getName());
        sudokuBoard = dao.read();
        readFromBoard();

    }

    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader primaryLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        App.setRoot(primaryLoader);
    }

    private void readFromBoard(){
        boolean isCompleted = true;
        //aktualizacja widoku na podstawie sudoku board
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                //przepisanie pól z sudokuBoard do widoku
                Button button = (Button) scene.lookup("#f" + i + j);
                button.setText(Integer.toString(sudokuBoard.get(i, j)));
                if(sudokuBoard.get(i, j) == 0){
                    isCompleted = false;
                    button.setText("");
                }

                //ustawianie stylu pól zmienialnych i niezmienialnych
                if(((SudokuBoardDecorator)sudokuBoard).isChangeable(i, j)){
                    button.setDisable(false);
                    button.setStyle("-fx-opacity:1;-fx-border-color:black;-fx-border-width:1 1 1 1;");
                } else {
                    button.setDisable(true);
                    button.setStyle("-fx-background-color:#1E90FF;-fx-opacity:1;-fx-border-color:black;-fx-border-width:1 1 1 1;");
                }
            }
        }

        //sprawdzanie poprawności
        Pane pane = (Pane) scene.lookup("#pane");
        if(sudokuBoard.checkBoard() && isCompleted){
            pane.setStyle("-fx-background-color:#86AF49;");
        } else if(sudokuBoard.checkBoard()){
            pane.setStyle(null);
        } else{
            pane.setStyle("-fx-background-color:F08080;");
        }
    }


}