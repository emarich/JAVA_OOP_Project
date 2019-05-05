package ViewContollers;

import OtherFunctionality.DataObserver;
import OtherFunctionality.PopUpAlert;
import UserObject.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    private static Database usersDatabase;
    private final DataObserver dataObserver;

    //Constructor
    public RegisterController (Database usersData) {
        usersDatabase = usersData;
        dataObserver = null;
    }
    public RegisterController (Database usersData, DataObserver texArea) {
        usersDatabase = usersData;
        dataObserver = texArea;
    }

    public void registerUser(Stage stage, Button registerBtn, TextField username, TextField password, ChoiceBox<String> userType) {
        registerBtn.setOnAction(event -> {
            //check if text fields are filled
            if((username.getText() == null || username.getText().trim().isEmpty()) ||
                    (password.getText() == null || password.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Username and password fields must be filled");
                alert.setTitle("Missing username or password");

            } else {
                try {
                    //check if username exists
                    if (usersDatabase.existingUser(username.getText())) {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Username \""+username.getText()+"\" is already used.");

                    } else {
                        usersDatabase.addUser(username.getText(), password.getText(), userType.getValue());

                        if (dataObserver != null) {
                            dataObserver.setUsersDatabase(usersDatabase);
                            dataObserver.update();
                        }

                        stage.close();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}