package ViewContollers;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import View.CheckUserStage;
import View.NewRequestStage;
import View.SignInScene;
import View.UserPropertyStage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController extends GuestController{
    private Database usersDatabase;
    private String username;

    //Constructor
    public UserController(Database usersDatabase, String username) {
        this.usersDatabase = usersDatabase;
        this.username = username;
    }

    //switch to makeStage
    public void newRequestClicked (MenuItem menuItem) {
        menuItem.setOnAction(event -> {
            try {
                NewRequestStage newRequestStage = new NewRequestStage(usersDatabase.getUser(username), usersDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void propertyClicked (MenuItem menuItem) {
        menuItem.setOnAction(event -> {
            try {
               UserPropertyStage userPropertyStage = new UserPropertyStage(usersDatabase.getUser(username));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
