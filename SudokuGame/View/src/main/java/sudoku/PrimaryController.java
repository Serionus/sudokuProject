package sudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        SudokuBoard.Difficulty chosenDifficulty = SudokuBoard.Difficulty.EASY;
        switch(levelChooser.getValue().toString()){
            case "Easy": chosenDifficulty = SudokuBoard.Difficulty.EASY;
                break;
            case 1: chosenDifficulty = SudokuBoard.Difficulty.MEDIUM;
                break;
            case "Hard": chosenDifficulty = SudokuBoard.Difficulty.HARD;
                break;
            case "Very Hard": chosenDifficulty = SudokuBoard.Difficulty.VERY_HARD;
                break;

        }
        FXMLLoader secondaryLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        App.setRoot(secondaryLoader);
        SecondaryController controller = secondaryLoader.getController();
        controller.initData(chosenDifficulty, languageBundle);

    }

    @FXML
    private void initialize(){
        changeToPolish();
    }

    private void changeToPolish(){
        refresh(Locale.getDefault());
    }

    private void changeToEnglish(){
        refresh(new Locale("en"));
    }

    private void refresh(Locale locale){
        authorsBundle = ResourceBundle.getBundle("sudoku.Authors", locale);
        languageBundle = ResourceBundle.getBundle("SudokuBundle", locale);
        difficulties = FXCollections.observableArrayList(languageBundle.getString("easy"),
                                                                languageBundle.getString("medium"),
                                                                languageBundle.getString("hard"),
                                                                languageBundle.getString("ultraHard"));

        levelChooser.setValue(difficulties.get(0));
        levelChooser.setItems(difficulties);

        producers.setText( languageBundle.getString("producers") + " " + authorsBundle.getString("nameOne") + " " +
                authorsBundle.getString("surnameOne") + " 229879 " +
                authorsBundle.getString("nameTwo") + " " +
                authorsBundle.getString("surnameTwo") + " 229908");

    }

    public static void main(String[] args) {
        App.main(args);
    }
}
