package sudoku;

import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimaryController {
    ResourceBundle authorsBundle;
    ResourceBundle languageBundle;
    ObservableList<String> difficulties;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @FXML
    private Label producers;

    @FXML
    private ChoiceBox levelChooser;

    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws NoGetterOrSetterException, CannotLoadFXMLException {
        SudokuBoard.Difficulty chosenDifficulty = SudokuBoard.Difficulty.EASY;

        int difficultyId = 0;
        for (int i = 0; i < difficulties.size(); i++) {
            if (difficulties.get(i).equals(levelChooser.getValue().toString())) {
                difficultyId = i;
            }
        }
        switch (difficultyId) {
            case 0: chosenDifficulty = SudokuBoard.Difficulty.EASY;
                break;
            case 1: chosenDifficulty = SudokuBoard.Difficulty.MEDIUM;
                break;
            case 2: chosenDifficulty = SudokuBoard.Difficulty.HARD;
                break;
            case 3: chosenDifficulty = SudokuBoard.Difficulty.VERY_HARD;
                break;
            default:
                break;
        }
        FXMLLoader secondaryLoader = new FXMLLoader(App.class.getResource("secondary.fxml"),
                                                                                languageBundle);
        try{
            App.setRoot(secondaryLoader);
        } catch (IOException e) {
            logger.info(languageBundle.getString("fxmlEception"));
            throw new CannotLoadFXMLException(languageBundle.getString("fxmlException"), e);
        }
        SecondaryController controller = secondaryLoader.getController();

        controller.initData(chosenDifficulty, languageBundle);
    }

    @FXML
    private void initialize() {
        changeToPolish();
    }

    @FXML
    private void changeToPolish() {
        refresh(Locale.getDefault());
    }

    @FXML
    private void changeToEnglish() {
        refresh(new Locale("en"));
    }

    private void refresh(Locale locale) {
        authorsBundle = ResourceBundle.getBundle("sudoku.Authors", locale);
        languageBundle = ResourceBundle.getBundle("SudokuBundle", locale);
        difficulties = FXCollections.observableArrayList(languageBundle.getString("easy"),
                        languageBundle.getString("medium"),
                        languageBundle.getString("hard"),
                        languageBundle.getString("ultraHard"));

        levelChooser.setItems(difficulties);
        levelChooser.setValue(difficulties.get(0));
        //difficulty.setText(languageBundle.getString("difficulty"));
        primaryButton.setText(languageBundle.getString("play"));
        producers.setText(languageBundle.getString("producers") + " "
                + authorsBundle.getString("nameOne") + " "
                + authorsBundle.getString("surnameOne") + " 229879 "
                + authorsBundle.getString("nameTwo") + " "
                + authorsBundle.getString("surnameTwo") + " 229908");

    }

    public static void main(String[] args) {
        App.main(args);
    }
}
