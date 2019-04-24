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


    public void makeLandClicked(TextField regNum, TextField address, TextField area, Stage stage) {
        if (existingRegNum(Integer.parseInt(regNum.getText()))) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "Land under register number "+regNum.getText()+" is already registered.");
        } else {
            try {
                Land land = new Land(Integer.parseInt(regNum.getText()), address.getText(),
                        Integer.parseInt(area.getText()), user.getOwner());

                land.setOwner(user.getOwner());

                user.getOwner().addLand(land);

                user.getOwner().setHaveLand(true);

                usersDatabase.getUsersDataHM().replace(user.getUsername(), user);

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                land = null;

                stage.close();

            } catch (ArrayIndexOutOfBoundsException e) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.INFORMATION,
                        "Please, write it in this format: \n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
            }
        }
    }

    //ERROR - not checking
    public boolean existingRegNum(int currentRegNum) {
        try {
            for (String s : usersDatabase.getUsersDataHM().keySet()) {
                //if user is not owner, break for loop
                if (!(usersDatabase.getUser(s).getIsOwner())) {
                    break;
                }
                if (!(usersDatabase.getUser(s).getOwner().getOwnedLands().isEmpty())) {
                    System.out.println(s);
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