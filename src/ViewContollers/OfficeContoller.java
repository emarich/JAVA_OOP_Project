package ViewContollers;

import OtherFunctionality.SerializableUtility;
import UserObject.User;
import View.RegisterStage;
import javafx.scene.control.MenuItem;


import java.util.HashMap;

public class OfficeContoller extends LogOut{
    private HashMap<String, User> userHashMap = new HashMap<>();

    public void addUser (MenuItem addUserItem) {
        addUserItem.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
