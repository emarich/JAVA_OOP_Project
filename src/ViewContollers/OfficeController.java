package ViewContollers;

import Offices.CadastralOffice;
import Offices.Office;
import View.CheckUserStage;
import View.RegisterStage;
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

    public void makeOwnerClicked (MenuItem makeOwnerItem) {
        makeOwnerItem.setOnAction(event -> {
            try {
                CheckUserStage checkUserStage = new CheckUserStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
