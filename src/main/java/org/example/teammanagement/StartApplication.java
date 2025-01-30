package org.example.teammanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 372);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        Model model = new Model();
       }

    public static void main(String[] args) {
        Model.getInstance().testConnection();
        launch();



    }
}
