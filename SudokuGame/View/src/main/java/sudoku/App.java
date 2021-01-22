package sudoku;
import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader primaryLoader = new FXMLLoader(App.class.getResource("primary.fxml"),
                ResourceBundle.getBundle("SudokuBundle", Locale.getDefault()));
        stage.setTitle("SudokuGame");
        stage.getIcons().add(new Image("sudokuicon.png"));
        scene = new Scene(primaryLoader.load(), 620, 510);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(FXMLLoader loader) throws IOException{
        scene.setRoot(loader.load());
    }

    static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

}