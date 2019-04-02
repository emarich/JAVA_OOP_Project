import View.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../View/officeScene.fxml"));
        LogIn login = new LogIn(primaryStage);

        Scene LoginScene = new Scene(login, 500, 500);

        primaryStage.setScene(LoginScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
