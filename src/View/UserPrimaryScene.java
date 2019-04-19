package View;

import UserObject.Database;
import ViewContollers.GuestController;
import ViewContollers.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPrimaryScene extends FlowPane{
    UserController userController;

    private VBox vBox = new VBox();

    //Menu
    private MenuBar menuBar = new MenuBar();

    private Menu userMenu = new Menu("User");
    private MenuItem accountItem = new MenuItem("Account");
    private MenuItem propertyItem = new MenuItem("Property");
    private MenuItem signOutItem = new MenuItem("Sign out");

    private Menu requestsMenu = new Menu("Requests");
    private MenuItem sendRequestItem = new MenuItem("Send request");
    private MenuItem pendingItem = new MenuItem("Pending requests");
    private MenuItem acceptedItem = new MenuItem("Accepted request");

    //Text fields
    private TextField searchField = new TextField();

    private TextArea textArea = new TextArea();

    //Constructor
    public UserPrimaryScene(Stage primaryStage, String username, Database usersDatabase) {
        userController = new UserController(usersDatabase);

        setScene(primaryStage);

        sceneEvents(primaryStage, usersDatabase);

        primaryStage.show();
    }

    //upravit do controlleru
    private void setScene(Stage primaryStage) {
        primaryStage.setScene(new Scene(this, primaryStage.getWidth(), primaryStage.getHeight()));

        this.getChildren().addAll(menuBar, textArea);
        this.setAlignment(Pos.TOP_LEFT);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        this.setHgap(20);

        //MenuBar
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(userMenu, requestsMenu);
        userMenu.getItems().addAll(accountItem, propertyItem, signOutItem);

        requestsMenu.getItems().addAll(sendRequestItem,pendingItem, acceptedItem);

        //VBox - textField, textArea
        vBox.getChildren().addAll(textArea);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(5, 30, 30, 10));
        vBox.prefWidthProperty().bind(primaryStage.widthProperty());
        //vBox.prefHeightProperty().bind(primaryStage.heightProperty().subtract(150));

        //textArea.prefHeightProperty().bind(vBox.heightProperty());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("There will be username, password and user type... maybe");
    }

    public void sceneEvents(Stage primaryStage, Database usersDatabase) {
        userController.switchSignInScene(signOutItem, primaryStage);
    }
}
