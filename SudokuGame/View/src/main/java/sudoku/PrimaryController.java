package sudoku;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PrimaryController {
    ResourceBundle authorsBundle;
    ResourceBundle languageBundle;
    ObservableList<String> difficulties;


    @FXML
    private Text difficulty;

    @FXML
    private Label producers;

    @FXML
    private ChoiceBox levelChooser;

    @FXML
    private void switchToSecondary() throws IOException {
        SudokuBoard.Difficulty chosenDifficulty = SudokuBoard.Difficulty.EASY;
        switch(levelChooser.getValue().toString()){
            case "Easy": chosenDifficulty = SudokuBoard.Difficulty.EASY;
                break;
            case "Medium": chosenDifficulty = SudokuBoard.Difficulty.MEDIUM;
                break;
            case "Hard": chosenDifficulty = SudokuBoard.Difficulty.HARD;
                break;
            case "Very Hard": chosenDifficulty = SudokuBoard.Difficulty.VERY_HARD;
                break;
        }
        FXMLLoader secondaryLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        App.setRoot(secondaryLoader);
        SecondaryController controller = secondaryLoader.getController();
        controller.initData(chosenDifficulty);

    }

    @FXML
    private void initialize(){
        changeToPolish();
    }

    public static void main(String[] args) {
        App.main(args);
    }
}
