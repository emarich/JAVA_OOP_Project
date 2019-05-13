package ViewContollers;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import View.*;
import javafx.scene.control.*;

public class OfficeController extends GuestController {

    public OfficeController(Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    //switch to makeStage
    public void makeMenuItemClicked (MenuItem menuItem, DataObserver textArea) {
        menuItem.setOnAction(event -> {
            try {
                CheckUserStage checkUserStage = new CheckUserStage(usersDatabase, menuItem.getId(), textArea);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void makeMenuItemClicked (MenuItem menuItem, String office) {
        menuItem.setOnAction(event -> {
            try {
                RequestsStage officeRequestsStage = new RequestsStage(usersDatabase.getUser(office));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
