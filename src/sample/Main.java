package sample;
import View.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        LogIn login = new LogIn();

        Scene LoginScene = new Scene(login, 500, 500);

        primaryStage.setScene(LoginScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
