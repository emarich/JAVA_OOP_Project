package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.Land;
import MyExceptions.AddressFormatException;
import MyExceptions.SameRegNumException;
import MyExceptions.WrongLandformInputException;
import OtherFunctionality.*;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void makeLandClicked(TextField regNum, TextField address, TextField area, ChoiceBox<String> landBox, Stage stage) {
        try {
            Address.correctAddress(address.getText());

            Land land = new Land(Integer.parseInt(regNum.getText()), address.getText(),
                    Integer.parseInt(area.getText()), user.getOwner(), landBox.getValue());

            CadastreSearch.compareRegNum(land, usersDatabase);

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
            } else if (e instanceof WrongLandformInputException){
                System.out.println(e.getMessage());
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