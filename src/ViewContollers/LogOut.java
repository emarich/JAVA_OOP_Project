package ViewContollers;

import UserObject.Database;
import View.SignInScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public abstract class LogOut {
    public void logOut(MenuItem signInBtn, Stage primaryStage, Database usersDatabase) {
        signInBtn.setOnAction(e -> {
            try {
                SignInScene signInScene = new SignInScene(primaryStage, usersDatabase);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
