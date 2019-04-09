package ViewContollers;

import View.RegisterStage;
import javafx.scene.control.*;

public class GuestController extends LogOut {
    //switch to register formula
    public void switchRegisterStage(MenuItem registerBtn) {
        registerBtn.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void changePromptText(Menu findMenu, RadioMenuItem ownerMenuItem, RadioMenuItem streetMenuItem, TextField textField) {
        findMenu.setOnShowing(event -> {
            if (streetMenuItem.isSelected()) {
                ownerMenuItem.setOnAction((ActionEvent) -> {
                    textField.setPromptText("Find owner");
                });
            } else if (ownerMenuItem.isSelected()){
                streetMenuItem.setOnAction((ActionEvent) -> {
                    textField.setPromptText("Find street or city");
                });
            }        });


    }

    public void printOwners() {

    }


}
