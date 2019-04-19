package ViewContollers;

import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import Owners.City;
import Owners.Owner;
import UserObject.Database;
import UserObject.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MakeOwnerController {
    private Database usersDatabase;

    public MakeOwnerController(Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    //if ownership = owner
    public void btnClicked (Stage stage, Button button, User user, TextField name, TextField date, TextField address) {
        button.setOnAction(event -> {
            user.setOwner(new Owner(name.getText(), date.getText(), address.getText()));
            user.setIsOwner(true);
            usersDatabase.getUsersDataHM().replace(user.getUsername(), user);
            SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());
            stage.close();
        });
    }

    // if ownership = city
    public void btnClicked (Stage stage, Button button, User user, TextField phoneNum, TextField email) {
        button.setOnAction(event -> {
            user.setOwner(new City(phoneNum.getText(), email.getText()));
            user.setIsOwner(true);
            usersDatabase.getUsersDataHM().put(user.getUsername(), user);
            SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());
            stage.close();
        });

    }



}
