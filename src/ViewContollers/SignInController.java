package ViewContollers;

import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import View.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignInController {

    private Database usersDatabase;

    //Constructor
    public SignInController(Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public void buttonClicked(Button signIn, TextField username, TextField password, ChoiceBox<String> userType,
                              Stage primaryStage) {
        signIn.setOnAction(e -> {

            //check, if text fields are filled
            if((username.getText() == null || username.getText().trim().isEmpty()) ||
                    (password.getText() == null || password.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Username and password fields must be filled");
                alert.setTitle("Missing username or password");

            } else {
                //if user exists under concrete username, switch to correct scene
                if (usersDatabase.existingUser(username.getText()) &&
                        userType.getValue().equalsIgnoreCase(usersDatabase.getUser(username.getText()).getUserType().toString())){
                    switchScenes(userType, username.getText(), primaryStage);
                //if user isn't found, or if inputs are incorrect
                } else {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Check if username or password or " +
                            "selected user type is correct");
                    alert.setTitle("Incorrect input");
                }
            }
        });
    }

    //choose which scene will be showing
    private void switchScenes (ChoiceBox<String> usertype, String username, Stage primaryStage) {
        if (usertype.getValue().equalsIgnoreCase("Citizen")) {
            UserPrimaryScene userStage = new UserPrimaryScene(primaryStage, username, usersDatabase);

        } else if (usertype.getValue().equalsIgnoreCase("Office")){
            OfficePrimaryScene officeStage = new OfficePrimaryScene(primaryStage, username, usersDatabase);

        } else {
            try {
                GuestPrimaryScene guestPrimaryScene = new GuestPrimaryScene(primaryStage, usersDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //if guest is checked in check box, automatically change scenes
    public void checkGuest(ChoiceBox<String> userTypeBox, Stage primaryStage) {
        userTypeBox.setOnAction(e -> {
            if (userTypeBox.getValue().equalsIgnoreCase("Guest")) {
                GuestPrimaryScene guestStage = new GuestPrimaryScene(primaryStage, usersDatabase);
            }
        });
    }

    //switch to register formula
    public void switchRegisterStage(Button registerBtn) {
        registerBtn.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage(usersDatabase);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
