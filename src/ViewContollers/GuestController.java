package ViewContollers;

import View.RegisterStage;
import View.SignInScene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GuestController {
    public void switchRegisterStage(MenuItem registerBtn) {
        registerBtn.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void switchSignInScene(MenuItem signInBtn, Stage primaryStage) {
        signInBtn.setOnAction(e -> {
            try {
                SignInScene signInScene = new SignInScene(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void changePromptText(Menu findMenu, RadioMenuItem ownerMenuItem, RadioMenuItem streetMenuItem, TextField textField) {
        findMenu.setOnShowing(event -> {
            if (streetMenuItem.isSelected()) {
                ownerMenuItem.setOnAction((ActionEvent) -> {
                    textField.setPromptText("Find owner");
                });
            } else if (ownerMenuItem.isSelected()){
                streetMenuItem.setOnAction((ActionEvent) -> {
                    textField.setPromptText("Find street or city");
                });
            }        });


    }


}
