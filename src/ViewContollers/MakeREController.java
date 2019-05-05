package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import CadasterObjects.TypeLand;
import OtherFunctionality.AddressFormatException;
import OtherFunctionality.DataObserver;
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
    private final DataObserver dataObserver;

    public MakeREController(User user, Database usersDatabase, DataObserver textArea) {
        this.usersDatabase = usersDatabase;
        this.user = user;
        this.dataObserver = textArea;
    }


    public void makeLandClicked(Button button, TextField regNum, TextField address, TextField area, ChoiceBox<String> REBox, Stage stage) {
        if (existingRegNum(Integer.parseInt(regNum.getText()))) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "Real estate under register number "+regNum.getText()+" is already registered.");
        } else {
            try {
                Address.correctAddress(address.getText());

                RealEstate realEstate = new RealEstate(Integer.parseInt(regNum.getText()), address.getText(),
                        Integer.parseInt(area.getText()), user.getOwner(), REBox.getValue());
                realEstate.setOwner(user.getOwner());
                user.getOwner().addRE(realEstate);
                user.getOwner().setHaveRealEstate(true);
                usersDatabase.getUsersDataHM().replace(user.getUsername(), user);

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                realEstate = null;

                dataObserver.setUsersDatabase(usersDatabase);
                dataObserver.update();

                stage.close();
            } catch (Exception e) {
                if (e instanceof IndexOutOfBoundsException) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                            e.getMessage());
                } else if (e instanceof AddressFormatException) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                            e.getMessage());
                    alert.setHeaderText("Error: address");
                } else {
                    e.printStackTrace();
                }
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

