package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        primaryStage.setTitle("Password maker NY");
        new passwordMaker(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
