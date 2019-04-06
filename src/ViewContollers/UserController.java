package ViewContollers;

import View.SignInScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class UserController {
    public void switchSignInScene(MenuItem signBtn, Stage primaryStage) {
        signBtn.setOnAction(e -> {
            try {
                SignInScene signInScene = new SignInScene(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
