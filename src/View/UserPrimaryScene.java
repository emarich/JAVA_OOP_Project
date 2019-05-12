package View;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import ViewContollers.GuestController;
import ViewContollers.UserController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPrimaryScene extends FlowPane{
    //Controller
    private UserController userController;

    private Database usersDatabase;

    private VBox vBox = new VBox();

    //Menu
    private MenuBar menuBar = new MenuBar();

    private Menu userMenu = new Menu();
    private MenuItem propertyItem = new MenuItem("Property");
    private MenuItem signOutItem = new MenuItem("Sign out");

    private Menu requestsMenu = new Menu("Requests");
    private MenuItem sendRequestItem = new MenuItem("Send request");
    private MenuItem pendingItem = new MenuItem("My requests");

    //Text fields
    private TextField searchField = new TextField();

    private DataObserver textArea = new DataObserver();

    private ChoiceBox<String> findChoiceBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "address", "owner"));

    //Constructor
    public UserPrimaryScene(Stage primaryStage, String username, Database usersDatabase) {
        userController = new UserController(usersDatabase, username);

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

        //MenuBar
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(userMenu, requestsMenu);
        userMenu.setText("User: "+username);
        userMenu.getItems().addAll(propertyItem, signOutItem);

        requestsMenu.getItems().addAll(sendRequestItem,pendingItem);

        //Choice box
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

    private void sceneEvents(Stage primaryStage) {
        textArea.setUsersDatabase(usersDatabase);
        textArea.update();

        userController.logOut(signOutItem, primaryStage, usersDatabase);

        findChoiceBox.setOnAction(event -> {
            userController.changePromptText(findChoiceBox, searchField);
        });

        userController.propertyClicked(propertyItem);

        userController.newRequestClicked(sendRequestItem);

        userController.myRequestsClicked(pendingItem);
    }
}
