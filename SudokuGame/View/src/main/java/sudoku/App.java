package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static FXMLLoader primaryLoader;
    private static FXMLLoader secondaryLoader;

    @Override
    public void start(Stage stage) throws IOException {
        primaryLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        stage.setTitle("SudokuGame");
        secondaryLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        scene = new Scene(primaryLoader.load(), 596, 508);
        stage.setScene(scene);
        stage.show();
    }

    public static FXMLLoader getPrimaryLoader() {
        return primaryLoader;
    }

    public static FXMLLoader getSecondaryLoader() {
        return secondaryLoader;
    }

    static void setRoot(FXMLLoader loader) throws IOException {
        scene.setRoot(loader.load());
    }

    static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

}