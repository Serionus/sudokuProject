package sudoku;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;

public class PrimaryController {
    ObservableList<String> difficulties = FXCollections.observableArrayList("Hard", "Mega Hard", "Super Mega Ultra Hard");
    private int chosenDifficulty = 1;  // 1 - hard, 2 - mega hard, 3 - super mega ultra hard

    @FXML
    private ChoiceBox levelChooser;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void initialize(){
        levelChooser.setValue("Easy");
        levelChooser.setItems(difficulties);
    }

    public static void main(String[] args) {
        App.main(args);
    }
}
