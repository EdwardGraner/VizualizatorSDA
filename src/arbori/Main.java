package arbori;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda `start` este apelată când aplicația JavaFX începe și
     * inițializează scena principală a aplicației.
     * @param primaryStage Stadiul principal al aplicației JavaFX.
     * @throws IOException Excepție în cazul în care nu se poate încărca
     * fișierul FXML.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Afisare.fxml")));
            primaryStage.setTitle("Arbori");
            primaryStage.setScene(new Scene(root, 410, 340));
        }
        catch (NullPointerException e){
            e.getMessage();
        }

        primaryStage.show();
    }
}
