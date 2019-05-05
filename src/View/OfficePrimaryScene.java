package View;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import ViewContollers.GuestController;
import ViewContollers.LogOut;
import ViewContollers.OfficeController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OfficePrimaryScene extends FlowPane {
    private OfficeController officeController;

    private Database usersDatabase;

    private VBox vBox = new VBox();

    private MenuBar menuBar = new MenuBar();

    private Menu citizensMenu = new Menu("Citizens");
    private MenuItem addUserItem = new MenuItem("Add User");
    private MenuItem makeOwnerItem = new MenuItem("Make owner from user");

    private Menu officeMenu = new Menu("Office");
    private MenuItem requestItem = new MenuItem("Requests");
    private MenuItem signOutItem = new MenuItem("Sign Out");

    private Menu landMenu = new Menu("Land");
    private MenuItem makeLandItem = new MenuItem("Make land");
    private MenuItem makeREItem = new MenuItem("Make real estate");
    private Menu realEstateMenu = new Menu("Real Estates");

    private Menu loggedUserText = new Menu();

    //Text fields
    private TextField searchField = new TextField();

    private final DataObserver textArea = new DataObserver();

    private ChoiceBox<String> findChoiceBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "address", "owner"));


    //Constructor
    public OfficePrimaryScene(Stage primaryStage, String username, Database usersDatabase) {
        officeController = new OfficeController(usersDatabase);

        this.usersDatabase = usersDatabase;

        setScene(primaryStage, username);

        sceneEvents(primaryStage);

        primaryStage.show();
    }

    private void setScene(Stage primaryStage, String username) {
        primaryStage.setScene(new Scene(this, primaryStage.getWidth(), primaryStage.getHeight()));

        vBox.getChildren().addAll(searchField, findChoiceBox, textArea);

        this.getChildren().addAll(menuBar, vBox);
        this.setAlignment(Pos.TOP_LEFT);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        this.setHgap(20);

        //Menu bar
        makeOwnerItem.setId("makeOwner");
        makeLandItem.setId("makeLand");
        makeREItem.setId("makeRE");
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(officeMenu, citizensMenu, landMenu, realEstateMenu, loggedUserText);

        citizensMenu.getItems().add(addUserItem);
        citizensMenu.getItems().add(makeOwnerItem);

        landMenu.getItems().add(makeLandItem);
        realEstateMenu.getItems().add(makeREItem);

        officeMenu.getItems().addAll(requestItem);

        loggedUserText.setText("User: "+username);
        loggedUserText.getItems().addAll(signOutItem);

        //ChoiceBox
        findChoiceBox.show();
        findChoiceBox.getSelectionModel().selectFirst();

        //VBox - textField, textArea
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(5, 30, 30, 10));
        vBox.prefWidthProperty().bind(primaryStage.widthProperty());
        vBox.prefHeightProperty().bind(primaryStage.heightProperty().subtract(150));

        searchField.setPromptText("Find land or real estate by address");
        searchField.setMaxWidth(300);

        textArea.prefHeightProperty().bind(vBox.heightProperty());
        textArea.setEditable(false);
        textArea.setWrapText(true);
    }

    public void sceneEvents(Stage primaryStage) {
        textArea.setUsersDatabase(usersDatabase);
        textArea.update();

        officeController.switchRegisterStage(addUserItem, textArea);

        officeController.logOut(signOutItem, primaryStage, usersDatabase);

        officeController.makeMenuItemClicked(makeOwnerItem, textArea);
        officeController.makeMenuItemClicked(makeLandItem, textArea);
        officeController.makeMenuItemClicked(makeREItem, textArea);

        //Change prompt text, when you select, what are you searching for
        findChoiceBox.setOnAction(event -> {
            officeController.changePromptText(findChoiceBox, searchField);
        });
    }
}
