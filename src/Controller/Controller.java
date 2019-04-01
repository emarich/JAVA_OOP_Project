package Controller;

import javafx.stage.Stage;

public class Controller {
    private  static Stage PrimaryStagec = new Stage();

    public Controller() {

    }

    public static void SetScene(String SetActiveScene) throws Exception {
        if (SetActiveScene == "UserScene") {
            new UserScreen(PrimaryStage);
        } else if (SetActiveScene == "LoginScene") {
            new LoginScreen(PrimaryStage);
        } else if (SetActiveScene == "RegistrationScene") {
            new RegistrationScreen(PrimaryStage);
        }
    }

    public static void SetScene() throws Exception {
        new LoginScreen(PrimaryStage);
    }
}
