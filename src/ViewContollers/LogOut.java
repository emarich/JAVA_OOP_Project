package ViewContollers;

import View.SignInScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public abstract class LogOut {
    public void logOut(MenuItem signInBtn, Stage primaryStage) {
        signInBtn.setOnAction(e -> {
            try {
                SignInScene signInScene = new SignInScene(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
