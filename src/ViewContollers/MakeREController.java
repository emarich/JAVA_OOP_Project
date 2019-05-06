package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import CadasterObjects.TypeLand;
import OtherFunctionality.*;
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


    public void makeREClicked(TextField regNum, TextField address, TextField area, ChoiceBox<String> REBox, Stage stage) {
        try {
            Address.correctAddress(address.getText());

            RealEstate realEstate = new RealEstate(Integer.parseInt(regNum.getText()), address.getText(),
                    Integer.parseInt(area.getText()), user.getOwner(), REBox.getValue());

            user.getOwner().existingRegNum(realEstate);

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
                } else if (e instanceof SameRegNumException){
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                            e.getMessage());
                    alert.setHeaderText("Error: same register number");
                } else {
                    e.printStackTrace();
                }
            }
        }
}

