package com.hania;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Main extends Application {

    private static Stage primaryStage;

    public static void setScene(AnchorPane root) {
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MessageBundle", Locale.getDefault());

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource("view/MainView.fxml"));

        AnchorPane root = loader.load();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
