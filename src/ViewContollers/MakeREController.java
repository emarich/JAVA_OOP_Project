package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//refactor later
public class MakeREController {
    private Database usersDatabase;
    private User user;

    public MakeREController(User user, Database usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.user = user;
    }


    public void makeLandClicked(Button button, TextField regNum, TextField address, TextField area, ChoiceBox<String> REBox, Stage stage) {
        if (existingRegNum(Integer.parseInt(regNum.getText()))) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "Real estate under register number "+regNum.getText()+" is already registered.");
        } else {
            try {
                RealEstate realEstate = new RealEstate(Integer.parseInt(regNum.getText()), address.getText(),
                        Integer.parseInt(area.getText()), user.getOwner(), REBox.getValue());

                realEstate.setOwner(user.getOwner());

                user.getOwner().addRE(realEstate);

                usersDatabase.getUsersDataHM().replace(user.getUsername(), user);

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                realEstate = null;

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
                System.out.println(s);
                //if user is not owner, break for loop
                if (!(usersDatabase.getUser(s).getIsOwner())) {
                    break;
                }
                if (!(usersDatabase.getUser(s).getOwner().getOwnedLands().isEmpty())) {
                    for (int i = 0; i <= usersDatabase.getUser(s).getOwner().getOwnedRE().size(); i++) {
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

    //public boolean matchingLand(int currentRegNum) {

    //}
}

