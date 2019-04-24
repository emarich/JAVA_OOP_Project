package ViewContollers;

import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import View.SignInScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public abstract class LogOut {

    public void logOut(MenuItem signInBtn, Stage primaryStage, Database usersData) {
        signInBtn.setOnAction(e -> {
            try {
                SignInScene signInScene = new SignInScene(primaryStage, usersData);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
