package ViewContollers;

import UserObject.Database;
import View.SignInScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class UserController {

    public void switchSignInScene(MenuItem signBtn, Stage primaryStage, Database usersDatabase) {
        signBtn.setOnAction(e -> {
            try {
                SignInScene signInScene = new SignInScene(primaryStage, usersDatabase);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
