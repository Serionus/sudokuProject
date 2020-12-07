package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");

    }

    public static void main(String[] args) {
        App.main(args);
    }
}
