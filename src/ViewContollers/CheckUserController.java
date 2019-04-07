package ViewContollers;

import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import UserObject.User;
import View.MakeOwnerScene;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckUserController {
    private Database usersDatabase = new Database();

    public void checkUser(TextField username, Button button, Stage stage) {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());

        button.setOnAction(event -> {
            //check, if user exists
            if(usersDatabase.existingUser(username.getText())) {
                User currentUser = usersDatabase.getUser(username.getText());

                //check, if there exists owner object
                if (currentUser.getIsOwner()) {
                    //if exists...
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Owner already exists in this user");
                } else {
                    if (currentUser.getUserType().toString().equalsIgnoreCase("Citizen")) {
                        try {
                            MakeOwnerScene makeOwnerScene = new MakeOwnerScene(currentUser, stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            MakeOwnerScene makeOwnerScene = new MakeOwnerScene(currentUser, stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Cannot find this user");
            }
        });
    }
}
