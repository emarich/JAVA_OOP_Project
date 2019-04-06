import View.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setTitle("ECO - Electronic Cadastral Office");
        //Parent root = FXMLLoader.load(getClass().getResource("../View/officeScene.fxml"));
        SignInScene signInScene = new SignInScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
