package ViewContollers;

import CadasterObjects.Address;
import MyExceptions.AddressFormatException;
import MyExceptions.EmailFormatException;
import MyExceptions.PhoneNumberFormatException;
import OtherFunctionality.*;
import Owners.City;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.format.DateTimeParseException;

public class MakeOwnerController {
    private Database usersDatabase;
    private final DataObserver dataObserver;

    public MakeOwnerController(Database usersDatabase, DataObserver textArea) {
        this.usersDatabase = usersDatabase;
        this.dataObserver = textArea;
    }

    //if ownership = owner
    public void btnClicked (Stage stage, Button button, User user, TextField name, ChoiceBox<String> gender, TextField date, TextField address) {
        button.setOnAction(event -> {
            if ((name.getText() == null || name.getText().trim().isEmpty()) ||
                    (date.getText() == null || date.getText().trim().isEmpty())||
                    (address.getText() == null || address.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                        "All fields must be filled");

            } else {
                try {
                    Owner.isValidDateFormat(date.getText());
                    Address.correctAddress(address.getText());
                    user.setOwner(new Owner(name.getText(), gender.getValue(), date.getText(), address.getText()));
                    user.setIsOwner(true);
                    usersDatabase.getUsersDataHM().replace(user.getUsername(), user);
                    SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                    dataObserver.setUsersDatabase(usersDatabase);
                    dataObserver.update();

                    stage.close();

                } catch (Exception e) {
                    if (e instanceof DateTimeParseException) {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                                "Date has a wrong format.\n" +
                                        "Right format: dd.MM.yyyy");
                    } else if (e instanceof AddressFormatException) {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                                e.getMessage());
                        alert.setHeaderText("Error: address");
                        System.out.println(e.getMessage());
                    } else if (e instanceof IndexOutOfBoundsException) {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                                e.getMessage());
                    } else {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    // if ownership = city
    public void btnClicked (Stage stage, Button button, User user, TextField phoneNum, TextField email) {
            button.setOnAction(event -> {
                if ((phoneNum.getText() == null || phoneNum.getText().trim().isEmpty()) ||
                        (email.getText() == null || email.getText().trim().isEmpty())) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                            "All fields must be filled");

                } else {
                    try {
                        Ownership.phoneNumberCheck(phoneNum.getText());
                        Ownership.emailCheck(email.getText());
                        user.setOwner(new City(phoneNum.getText(), email.getText()));
                        user.setIsOwner(true);
                        usersDatabase.getUsersDataHM().put(user.getUsername(), user);
                        SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                        dataObserver.setUsersDatabase(usersDatabase);
                        dataObserver.update();

                        stage.close();
                    } catch (Exception e) {
                        if (e instanceof EmailFormatException) {
                            PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                                    "Email has a wrong format.\n " +
                                            "Error: "+e.getMessage());
                            System.out.println(e.getMessage());
                        } else if (e instanceof PhoneNumberFormatException) {
                            PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                                    "Phone number has a wrong format.\n" +
                                            "Error: "+e.getMessage());
                            System.out.println(e.getMessage());
                        } else {
                            e.printStackTrace();
                        }

                    }
                }

            });
    }



}
