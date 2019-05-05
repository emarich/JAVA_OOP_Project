package View;

import OtherFunctionality.DataObserver;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import ViewContollers.GuestController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import javax.xml.crypto.Data;

public class GuestPrimaryScene extends FlowPane {
    //Controller
    private GuestController guestController;

    private Database usersDatabase;

    private VBox vBox = new VBox();

    //Menu
    private MenuBar menuBar = new MenuBar();

    private Menu actualTimeDate = new Menu();

    private Menu register = new Menu("Register");
    private MenuItem registerItem = new MenuItem("Register user");
    private MenuItem signInItem = new MenuItem("Sign in");

    //Text fields
    private TextField searchField = new TextField();

    private DataObserver textArea = new DataObserver();

    private ChoiceBox<String> findChoiceBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "address", "owner"));

    //Constructor
    public GuestPrimaryScene(Stage primaryStage, Database usersDatabase) {
        guestController = new GuestController(usersDatabase);

        this.usersDatabase = usersDatabase;

        setScene(primaryStage);

        sceneEvents(primaryStage);

        primaryStage.show();
    }

    private void setScene(Stage primaryStage) {
        primaryStage.setScene(new Scene(this, primaryStage.getWidth(), primaryStage.getHeight()));


        vBox.getChildren().addAll(searchField, findChoiceBox, textArea);

        this.getChildren().addAll(menuBar, vBox);
        this.setAlignment(Pos.TOP_LEFT);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        this.setHgap(20);

        //MenuBar
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(register, actualTimeDate);

       //Choice box
        findChoiceBox.show();
        findChoiceBox.getSelectionModel().selectFirst();

        register.getItems().addAll(registerItem, signInItem);

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

    private void sceneEvents (Stage primaryStage) {
        textArea.setUsersDatabase(usersDatabase);
        textArea.update();

        //Register
        guestController.switchRegisterStage(registerItem);

        //Sign in
        guestController.logOut(signInItem, primaryStage, usersDatabase);

        //Change prompt text, when you select, what are you searching for
        findChoiceBox.setOnAction(event -> {
            guestController.changePromptText(findChoiceBox, searchField);
        });
    }


}