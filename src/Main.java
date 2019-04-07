import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import UserObject.User;
import View.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application{
    private Database usersDatabase = new Database();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //usersDatabase.deleteAllUsers();
        //SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        if (usersDatabase.getUsersDataHM() == null) {
            System.out.println("There are no users in database.\nPlease, register");
        } else {
            usersDatabase.printUsers();
        }

        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setTitle("ECO - Electronic Cadastral Office");
        SignInScene signInScene = new SignInScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
