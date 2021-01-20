package sudoku;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import java.util.Locale;
public class SecondaryController {

    @FXML
    FileChooser fileChooser = new FileChooser();

    @FXML
    Button exitButton;

    @FXML
    Button loadButton;
    @FXML
    Button saveButton;
    @FXML
    Label savenameLine;

    Scene scene;
    Button activeField;
    BacktrackingSudokuSolver solver;
    SudokuBoard sudokuBoard;
    FileSudokuBoardDao dao;
    ResourceBundle bundle = ResourceBundle.getBundle("SudokuBundle", Locale.getDefault());


    public void initData(SudokuBoard.Difficulty difficulty, ResourceBundle bundle)
            throws NoGetterOrSetterException {
        scene = App.getScene();
        this.bundle = bundle;
        solver = new BacktrackingSudokuSolver();
        sudokuBoard = new ChangebleFields(new SudokuBoard(solver, difficulty));
        sudokuBoard.solveGame();
        sudokuBoard = sudokuBoard.getDifficulty().removeFields(sudokuBoard);
        ((ChangebleFields)sudokuBoard).markChangeableFields();
        exitButton.setText(bundle.getString("close"));
        bidirectionalBinding();
    }

    @FXML
    private void boardButtonAction(ActionEvent event) {
        activeField = (Button) event.getSource();
    }

    @FXML
    private void keyboardButtonAction(ActionEvent event) {
        Button thisField = (Button) event.getSource();
        try {
            activeField.setText(thisField.getText());
        } catch (NullPointerException antoni) {
            antoni.initCause(new ButtonNotSelectedException());
            throw antoni;
        }
        checkCorrection();
    }

    @FXML
    private void markAsUncertain(ActionEvent event) {
        if (activeField.getStyle().equals("-fx-background-color:#7FFFD4;")) {
            activeField.setStyle(null);
        } else {
            activeField.setStyle("-fx-background-color:#7FFFD4;");
        }
    }

    @FXML
    private void save(ActionEvent event) throws WrongClassUsedException {
        TextField newSave = (TextField) scene.lookup("#saveName");
        dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.createFileDao(newSave.getText());
        dao.write(sudokuBoard);
    }

    @FXML
    private void load(ActionEvent event) throws WrongFileChosenException, NoGetterOrSetterException {
        fileChooser.setTitle("Wybierz zapis");
        File selectedDirectory = fileChooser.showOpenDialog(scene.getWindow());
        dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.createFileDao(selectedDirectory.getName());
        sudokuBoard = dao.read();
        bidirectionalBinding();

    }

    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader primaryLoader = new FXMLLoader(App.class.getResource("primary.fxml"), bundle);
        App.setRoot(primaryLoader);
    }

    private void bidirectionalBinding() throws NoGetterOrSetterException {
        JavaBeanIntegerProperty valueProperty;
        JavaBeanIntegerPropertyBuilder builder = JavaBeanIntegerPropertyBuilder.create();
        StringConverter<Number> converter = prepareConverter();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    //wiązanie dwukierunkowe
                    Button button = (Button) scene.lookup("#f" + i + j);
                    valueProperty = builder.bean(sudokuBoard.getField(i, j)).name("value").build();
                    button.textProperty().bindBidirectional(valueProperty, converter);

                    //ustawianie stylu pól zmienialnych i niezmienialnych
                    if (((ChangebleFields)sudokuBoard).isChangeable(i, j)) {
                        button.setDisable(false);
                        button.setStyle("-fx-opacity:1;"
                                + "-fx-border-color:black;-fx-border-width:1 1 1 1;");
                    } else {
                        button.setDisable(true);
                        button.setStyle("-fx-background-color:#1E90FF;"
                                + "-fx-opacity:1;-fx-border-color:black;-fx-border-width:1 1 1 1;");
                    }
                } catch (NoSuchMethodException e) {
                    throw new NoGetterOrSetterException("Nie ma mozliwosci stworzyc beana", e);
                }

            }
        }
    }

    public void checkCorrection() {
        boolean isCompleted = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i, j) == 0) {
                    isCompleted = false;
                }
            }
        }
        Pane pane = (Pane) scene.lookup("#pane");
        if (sudokuBoard.checkBoard() && isCompleted) {
            pane.setStyle("-fx-background-color:#86AF49;");
        } else if (sudokuBoard.checkBoard()) {
            pane.setStyle(null);
        } else {
            pane.setStyle("-fx-background-color:F08080;");
        }
    }

    private StringConverter<Number> prepareConverter() {
        return new NumberStringConverter() {
            @Override
            public Number fromString(String value) {
                if (value.equals("")) {
                    return 0;
                } else {
                    return super.fromString(value);
                }
            }

            @Override
            public String toString(Number value) {
                if (value.equals(0)) {
                    return "";
                } else {
                    return super.toString(value);
                }
            }
        };
    }
}