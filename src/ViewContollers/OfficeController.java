package ViewContollers;

import Offices.CadastralOffice;
import Offices.Office;
import View.CheckUserStage;
import View.MakeLandStage;
import View.RegisterStage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class OfficeController extends LogOut{
    private Office office = new CadastralOffice();


    public void addUser (MenuItem addUserItem) {
        addUserItem.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    //switch to make owner formula stage
    public void makeOwnerClicked (MenuItem makeOwnerItem) {
        makeOwnerItem.setOnAction(event -> {
            try {
                CheckUserStage checkUserStage = new CheckUserStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //switch to make land formula
    public void makeLandClicked(MenuItem makeLandItem) {
        makeLandItem.setOnAction(event -> {
            try {
                MakeLandStage makeLandStage = new MakeLandStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
