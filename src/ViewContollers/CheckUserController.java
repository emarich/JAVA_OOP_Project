package ViewContollers;

import OtherFunctionality.DataObserver;
import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import UserObject.User;
import View.CheckUserStage;
import View.MakeLandStage;
import View.MakeOwnerScene;
import View.MakeREStage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckUserController {
    private Database usersDatabase;
    private String menuItemTxt;
    private final DataObserver textArea; //be aware

    public CheckUserController (Database usersDatabase, String text, DataObserver textArea) {
        this.usersDatabase = usersDatabase;
        menuItemTxt = text;
        this.textArea = textArea;
    }

    public void actions(TextField username, Node node, Stage stage) {
        if (node instanceof Button) {
            ((Button)node).setOnAction(event -> checkUser(username, stage, textArea));
        } else if (node instanceof TextField) {
            ((TextField)node).setOnAction(event -> checkUser(username, stage, textArea));
        }

    }

    //check user, if he have already object Owner created
    public void checkUser(TextField username, Stage stage, DataObserver textArea) {
        //check, if user exists
        if(usersDatabase.existingUser(username.getText())) {
            User currentUser = usersDatabase.getUser(username.getText());
//_______________________________________________________makeOwner
            //check, which menu button was clicked
            if (menuItemTxt.equalsIgnoreCase("makeOwner")) {

                //check, if there exists owner object
                if (currentUser.getIsOwner()) {
                    //if owner exists...
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Owner already exists in this user");

                } else {
                    try {
                        MakeOwnerScene makeOwnerScene = new MakeOwnerScene(currentUser, stage, usersDatabase, textArea);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//_______________________________________________________makeLand
            } else if (menuItemTxt.equalsIgnoreCase("makeLand")) {

                //check, if there exists owner object
                if (currentUser.getIsOwner()) {
                    //if owner exists...
                    try {
                        stage.close();
                        MakeLandStage makeLandStage = new MakeLandStage(currentUser, usersDatabase, textArea);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ownerObjectMissing(currentUser, stage, usersDatabase);
                }
//_______________________________________________________makeRE
            } else if (menuItemTxt.equalsIgnoreCase("makeRE")) {
                //check, if there exists owner object
                if (currentUser.getIsOwner()) {
                    //if owner exists...
                    try {
                        stage.close();
                        MakeREStage makeREStage = new MakeREStage(currentUser, usersDatabase, textArea);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ownerObjectMissing(currentUser, stage, usersDatabase);
                }
//_______________________________________________________If cannot find ID
            } else {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                        "I can't find a path :(");
            }

        } else {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Cannot find this user");
        }
    }

    private void ownerObjectMissing(User user, Stage stage, Database usersDatabase) {
        try {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "User is not owner, at first, you must create it.");
            MakeOwnerScene makeOwnerScene = new MakeOwnerScene(user, stage, usersDatabase, textArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
