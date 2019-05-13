package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.RealEstate;
import MyExceptions.AddressFormatException;
import MyExceptions.SameRegNumException;
import MyExceptions.WrongInputException;
import OtherFunctionality.*;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Vytvára nové budovy, ktoré vie vytvárať administrátor pre všetkych používateľov.
 */
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
            if (!((Integer.parseInt(regNum.getText()) >= 1000) && (Integer.parseInt(regNum.getText()) <= 9999))) {
                throw new WrongInputException("Register number must have 4 digits.");
            }

            Address.correctAddress(address.getText());


            RealEstate realEstate = new RealEstate(Integer.parseInt(regNum.getText()), address.getText(),
                    Integer.parseInt(area.getText()), user.getOwner(), REBox.getValue());

            CadastreSearch.compareRegNum(realEstate, usersDatabase);

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
                } else if (e instanceof WrongInputException) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                            e.getMessage());
                    alert.setHeaderText("Error: register number format");
                } else {
                    e.printStackTrace();
                }
            }
        }
}

