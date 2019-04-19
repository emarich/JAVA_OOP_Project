package ViewContollers;

import CadasterObjects.Address;
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
public class MakeLandController {
    private Database usersDatabase;
    private User user;

    public MakeLandController(User user, Database usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.user = user;
    }


    public void makeLandClicked(Button button, String usernameTxt, TextField regNum, TextField address, TextField area, Stage stage) {
        if (existingRegNum(Integer.parseInt(regNum.getText()))) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "Land under register number "+regNum.getText()+" is already registered.");
        } else {
            try {
                Land land = new Land(Integer.parseInt(regNum.getText()), address.getText(),
                        Integer.parseInt(area.getText()), user.getOwner());

                land.setOwner(user.getOwner());

                user.getOwner().addLand(land);

                usersDatabase.getUsersDataHM().replace(usernameTxt, user);

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                land = null;

            } catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println("Please, write it in this format: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.INFORMATION,
                        "Please, write it in this format: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
            }
        }
    }

    public boolean existingRegNum(int currentRegNum) {
        try {
            for (String s : usersDatabase.getUsersDataHM().keySet()) {
                System.out.println(s);
                //if user is not owner, break for loop
                if (!(usersDatabase.getUser(s).getIsOwner())) {
                    break;
                }
                if (!(usersDatabase.getUser(s).getOwner().getOwnedLands().isEmpty())) {
                    for (int i = 0; i <= usersDatabase.getUser(s).getOwner().getOwnedLands().size(); i++) {
                        if (usersDatabase.getUser(s).getOwner().getOwnedLands().get(i).getRegisterNum() == currentRegNum) {
                            return true;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
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
