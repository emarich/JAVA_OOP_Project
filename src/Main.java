import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
import UserObject.User;
import View.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application{
    private Database usersDatabase = new Database();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //usersDatabase.deleteAllUsers();
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());

        for (String s : usersDatabase.getUsersDataHM().keySet()) {
            User user = usersDatabase.getUser(s);
            if (user.getIsOwner()) {
                Ownership owner = user.getOwner();
                if (owner.getHaveLand()) {
                    for (Land l : owner.getOwnedLands()) {
                        if (l.getHaveRE()) {
                            System.out.println(l.getRegisterNum()+" land "+l.getHaveRE());
                        }
                    }
                }
                if (owner.getHaveRealEstate()) {
                    for (RealEstate l : owner.getOwnedRE()) {
                        if (l.getHaveLand()) {
                            System.out.println(l.getRegisterNum()+" re "+l.getHaveLand());
                        }
                    }
                }
            }
        }

        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setTitle("ECO - Electronic Cadastral Office");
        SignInScene signInScene = new SignInScene(primaryStage, usersDatabase);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
