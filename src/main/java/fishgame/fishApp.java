package fishgame;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class fishApp extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Fiss");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fishApp.fxml"))));
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            // System.out.println("FULLSCREEEN");
        });
        primaryStage.show();
    }
    
}
