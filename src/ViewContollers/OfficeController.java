package ViewContollers;

import Offices.CadastralOffice;
import Offices.Office;
import OtherFunctionality.DataObserver;
import OtherFunctionality.PrintCadastreThread;
import UserObject.Database;
import View.CheckUserStage;
import View.MakeLandStage;
import View.RegisterStage;
import View.SignInScene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OfficeController extends GuestController {

    private Office office = new CadastralOffice();

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
}
