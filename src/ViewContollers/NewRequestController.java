package ViewContollers;

import CadasterObjects.Address;
import CadasterObjects.Land;
import MyExceptions.AddressFormatException;
import MyExceptions.SameRegNumException;
import MyExceptions.WrongLandformInputException;
import OtherFunctionality.CadastreSearch;
import OtherFunctionality.DataObserver;
import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewRequestController {
    private Database usersDatabase;
    private User user;

    public NewRequestController(User user, Database usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.user = user;
    }


}
