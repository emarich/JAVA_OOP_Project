package ViewContollers;

import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class RegisterController {
    private Database usersDatabase = new Database();

    public void registerUser(Stage stage, Button registerBtn, TextField username, TextField password, ChoiceBox<String> userType) {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        registerBtn.setOnAction(event -> {
            if (usersDatabase.existingUser(username.getText())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Username "+username+" is already used.");
            } else {
                usersDatabase.addUser(username.getText(), password.getText(), userType.getValue());
                stage.close();
            }
        });
    }

    //bug: created office user and right after that i cannot sign in as office... so program must be restarted
}