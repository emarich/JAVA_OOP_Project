package ViewContollers;

import Offices.CadastralOffice;
import Offices.Office;
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


    //switch to make owner formula stage
    public void makeOwnerClicked (MenuItem makeOwnerItem) {
        makeOwnerItem.setOnAction(event -> {
            try {
                CheckUserStage checkUserStage = new CheckUserStage(usersDatabase, makeOwnerItem.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //switch to make land formula
    public void makeLandClicked(MenuItem makeLandItem) {
        makeLandItem.setOnAction(event -> {
            try {
                CheckUserStage checkUserStage = new CheckUserStage(usersDatabase, makeLandItem.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //switch to make real estate formula
    public void makeREClicked(MenuItem makeItem) {
        makeItem.setOnAction(event -> {
            try {
                CheckUserStage checkUserStage = new CheckUserStage(usersDatabase, makeItem.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
