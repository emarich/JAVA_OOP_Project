package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.Land;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

//refactor later
public class MakeLandController {
    private Database usersDatabase;
    private User user;
    private final DataObserver dataObserver;

    public MakeLandController(User user, Database usersDatabase, DataObserver textArea) {
        this.usersDatabase = usersDatabase;
        this.user = user;
        this.dataObserver = textArea;
    }

    public void makeLandClicked(TextField regNum, TextField address, TextField area, Stage stage) {
        if (existingRegNum(Integer.parseInt(regNum.getText()))) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "Land under register number "+regNum.getText()+" is already registered.");
        } else {
            try {
                Address.correctAddress(address.getText());

                Land land = new Land(Integer.parseInt(regNum.getText()), address.getText(),
                        Integer.parseInt(area.getText()), user.getOwner());
                land.setOwner(user.getOwner());
                user.getOwner().addLand(land);
                user.getOwner().setHaveLand(true);
                usersDatabase.getUsersDataHM().replace(user.getUsername(), user);

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                land = null;

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
                //if user is not owner, break for loop
                if (!(usersDatabase.getUser(s).getIsOwner())) {
                    break;
                }
                if (!(usersDatabase.getUser(s).getOwner().getOwnedLands().isEmpty())) {
                    System.out.println(s);
                    /*for (int i = 0; i <= usersDatabase.getUser(s).getOwner().getOwnedLands().size(); i++) {
                        if (usersDatabase.getUser(s).getOwner().getOwnedLands().get(i).getRegisterNum() == currentRegNum) {
                            return true;
                        }
                    }*/
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}