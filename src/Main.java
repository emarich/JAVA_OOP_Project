import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import View.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    private Database usersDatabase = new Database();

    @Override
    public void start(Stage primaryStage) throws Exception{
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        usersDatabase.printUsers();

        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setTitle("ECO - Electronic Cadastral Office");
        SignInScene signInScene = new SignInScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
