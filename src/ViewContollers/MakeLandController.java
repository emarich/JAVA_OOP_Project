package ViewContollers;

import CadasterObjects.Land;
import CadasterObjects.TypeLand;
import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
import UserObject.User;
import View.MakeOwnerScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//refactor later
//NullPointerException problem!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class MakeLandController {
    private Database usersDatabase = new Database();
    private User user = new User();


    public void checkUserIfIsOwner(Button button, TextField usernameTxt, Stage stage) {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        user = usersDatabase.getUser(usernameTxt.getText());

        button.setOnAction(event -> {
            try {
                if(user.getIsOwner()) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.INFORMATION, "User is owner.");
                } else if (user.getOwner() == null){
                    try {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                                "User is not owner, at first, you must create it.");
                        //stage.close();
                        MakeOwnerScene makeOwnerScene = new MakeOwnerScene(user, stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (NullPointerException eNull) {
                eNull.printStackTrace();
            }
        });
        user = null;
    }

    public void makeLandClicked(Button button, TextField usernameTxt, TextField regNum, TextField city, TextField area, Stage stage) {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        user = usersDatabase.getUser(usernameTxt.getText());

        button.setOnAction(event -> {
            if(user.getIsOwner()) {
                if (existingRegNum(usersDatabase, Integer.parseInt(regNum.getText()))) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                            "Land under register num "+regNum.getText()+" is already registered.");
                } else {
                    Land land = new Land();
                    land.setRegisterNum(Integer.parseInt(regNum.getText()));
                    land.setCity(city.getText());
                    land.setArea(Integer.parseInt(area.getText()));

                    if(user.getOwner().getClass() == Owner.class) {
                        land.setTypeLand(TypeLand.PUBLIC);
                    } else {
                        land.setTypeLand(TypeLand.PRIVATE);
                    }
                    land.setOwner(user.getOwner());

                    user.getOwner().addLand(land);

                    land = null;
                }
            } else {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                        "User is not owner, at first, you must create it.");
                stage.close();

                try {
                    MakeOwnerScene makeOwnerScene = new MakeOwnerScene(user, stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public boolean existingRegNum(Database usersDatabase, int currentRegNum) {
        for (String s : usersDatabase.getUsersDataHM().keySet()) {
            for (int i = 0; i < usersDatabase.getUser(s).getOwner().getOwnedLands().size(); i++) {
                if (usersDatabase.getUser(s).getOwner().getOwnedLands().get(i).getRegisterNum() == currentRegNum) {
                    return true;
                }
            }
        }
        return false;
    }
}

/*
if(user.getIsOwner()) {
                Land land = new Land();
                land.setRegisterNum(Integer.parseInt(regNum.getText()));
                land.setCity(city.getText());
                land.setArea(Integer.parseInt(area.getText()));

                if(user.getOwner().getClass() == Owner.class) {
                    land.setTypeLand(TypeLand.PUBLIC);
                } else {
                    land.setTypeLand(TypeLand.PRIVATE);
                }

                land.setOwner(user.getOwner());

            } else {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                        "User is not owner, at first, you must create it.");
                stage.close();

                try {
                    MakeOwnerScene makeOwnerScene = new MakeOwnerScene(user, stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
 */
