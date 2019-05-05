package ViewContollers;

import OtherFunctionality.DataObserver;
import OtherFunctionality.PrintCadastreThread;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import View.RegisterStage;
import javafx.scene.control.*;

public class GuestController extends LogOut {
    protected Database usersDatabase;

    //Constructor
    public GuestController (Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public GuestController() {
        usersDatabase = new Database();
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
    }

    //switch to register formula
    public void switchRegisterStage(MenuItem registerBtn) {
        registerBtn.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage(usersDatabase);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void changePromptText(ChoiceBox<String> userTypeBox, TextField textField) {
        if (userTypeBox.getValue().equals("owner")) {
            textField.setPromptText("Find owner by name");
        } else if (userTypeBox.getValue().equals("address")){
            textField.setPromptText("Find land or real estate by address");
        }
    }



}
