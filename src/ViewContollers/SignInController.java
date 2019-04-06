package ViewContollers;

import OtherFunctionality.PopUpAlert;
import View.GuestPrimaryScene;
import View.OfficePrimaryScene;
import View.RegisterStage;
import View.UserPrimaryScene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignInController {

    public boolean checkUser(String username, String userType) {
        //load database and check, if user exists
        System.out.println(username+" "+userType);
        return true;
    }

    public void signInClicked (Button signIn, TextField username, TextField password, ChoiceBox<String> userType, Stage primaryStage) {
        signIn.setOnAction(e -> {
            if((username.getText() == null || username.getText().trim().isEmpty()) || (password.getText() == null || password.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Username and/or password must be filled");
                alert.setTitle("Missing username or password");
            } else {
                if (checkUser(username.getText(), userType.getValue())){
                    switch (userType.getValue() ) {
                        case "User":
                            UserPrimaryScene userStage = new UserPrimaryScene(primaryStage, username.getText());
                            break;
                        case "Office":
                            OfficePrimaryScene officeStage = new OfficePrimaryScene(primaryStage, username.getText());
                            break;
                    }
                } else {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Check if username or password is correct");
                    alert.setTitle("Cannot find user");
                }
            }
        });
    }

    public void checkGuest(ChoiceBox<String> userTypeBox, Stage primaryStage) {
        userTypeBox.setOnAction(e -> {
            if (userTypeBox.getValue().equals("Guest")) {
                GuestPrimaryScene guestStage = new GuestPrimaryScene(primaryStage);
            }
        });
    }

    public void switchRegisterStage(Button registerBtn) {
        registerBtn.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
