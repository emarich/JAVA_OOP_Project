import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import View.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *<h1>ECO - Electronic Cadastral Office<h1/>
 * Štart do prihlasovacieho okna.
 * @author Ema Richnakova
 */

public class Main extends Application{
    private Database usersDatabase = new Database();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //usersDatabase.deleteAllUsers();
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());

        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setTitle("ECO - Electronic Cadastral Office");
        SignInScene signInScene = new SignInScene(primaryStage, usersDatabase);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
