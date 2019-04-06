package ViewContollers;

import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import UserObject.User;
import View.GuestPrimaryScene;
import View.OfficePrimaryScene;
import View.RegisterStage;
import View.UserPrimaryScene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.HashMap;

public class SignInController {
    private Database usersDatabase = new Database();

    public void buttonClicked(Button signIn, TextField username, TextField password, ChoiceBox<String> userType, Stage primaryStage) {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        signIn.setOnAction(e -> {
            if((username.getText() == null || username.getText().trim().isEmpty()) || (password.getText() == null || password.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Username and/or password must be filled");
                alert.setTitle("Missing username or password");
            } else {
                if (usersDatabase.existingUser(username.getText())){
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
