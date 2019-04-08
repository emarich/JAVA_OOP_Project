package View;

import ViewContollers.OfficeController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class OfficePrimaryScene extends FlowPane {
    private OfficeController officeController = new OfficeController();

    private MenuBar menuBar = new MenuBar();

    private Menu citizensMenu = new Menu("Citizens");
    private MenuItem addUserItem = new MenuItem("Add User");
    private MenuItem makeOwnerItem = new MenuItem("Make owner from user");

    private Menu officeMenu = new Menu("Office");
    private MenuItem requestItem = new MenuItem("Requests");
    private MenuItem signOutItem = new MenuItem("Sign Out");

    private Menu landMenu = new Menu("Land");
    private MenuItem makeLandItem = new MenuItem("Make land");
    private MenuItem makeREItem = new MenuItem("Make real etsate");
    private Menu realEstateMenu = new Menu("Real Estates");

    private Menu loggedUserText = new Menu();


    //Constructor
    public OfficePrimaryScene(Stage primaryStage, String username) {

        setScene(primaryStage, username);

        sceneEvents(primaryStage);

        primaryStage.show();
    }

    private void setScene(Stage primaryStage, String username) {
        primaryStage.setScene(new Scene(this, primaryStage.getWidth(), primaryStage.getHeight()));

        this.getChildren().addAll(menuBar);

        this.setAlignment(Pos.TOP_LEFT);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        //Menu bar
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(officeMenu, citizensMenu, landMenu, realEstateMenu, loggedUserText);

        citizensMenu.getItems().add(addUserItem);
        citizensMenu.getItems().add(makeOwnerItem);

        landMenu.getItems().add(makeLandItem);
        realEstateMenu.getItems().add(makeREItem);

        officeMenu.getItems().addAll(requestItem, signOutItem);

        loggedUserText.setText("User: "+username);
    }

    public void sceneEvents(Stage primaryStage) {

        officeController.addUser(addUserItem);

        officeController.logOut(signOutItem, primaryStage);

        officeController.makeOwnerClicked(makeOwnerItem);

        officeController.makeLandClicked(makeLandItem);
    }
}
