import View.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Electronic Cadastral Office");
        //Parent root = FXMLLoader.load(getClass().getResource("../View/officeScene.fxml"));
        SignInScene signInScene = new SignInScene(primaryStage);

        Scene signIn = new Scene(signInScene);
        signInScene.setPrefSize(500, 500);

        primaryStage.setScene(signIn);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
