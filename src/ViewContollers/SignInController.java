package ViewContollers;

import OtherFunctionality.PopUpAlert;
import View.GuestPrimaryScene;
import View.OfficePrimaryStage;
import View.UserPrimaryStage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

    public boolean checkUser(String username, String userType) {
        //load database and check, if user exists
        System.out.println(username+" "+userType);
        return true;
    }

    public void signInClicked (Button signIn, TextField username, TextField password, String userType, Stage primaryStage) {
        signIn.setOnAction(e -> {
            if((username.getText() == null || username.getText().trim().isEmpty()) || (password.getText() == null || password.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Username and/or password must be filled");
                alert.setTitle("Missing username or password");
            } else {
                if (checkUser(username.getText(), userType)){
                    switch (userType) {
                        case "User":
                            UserPrimaryStage userStage = new UserPrimaryStage(primaryStage, username.getText());
                            break;
                        case "Office":
                            OfficePrimaryStage officeStage = new OfficePrimaryStage(primaryStage, username.getText());
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

    public void switchRegisterStage() {

    }
}
