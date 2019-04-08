package View;

import ViewContollers.GuestController;
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

public class GuestPrimaryScene extends FlowPane {
    GuestController guestController = new GuestController();

    private VBox vBox = new VBox();
    //Menu
    private MenuBar menuBar = new MenuBar();

    private Menu findMenu = new Menu("Find...");
    private ToggleGroup findGroup = new ToggleGroup();
    private RadioMenuItem findStreetCity = new RadioMenuItem("street or city");
    private RadioMenuItem findOwner = new RadioMenuItem("owner");

    private Menu register = new Menu("Register");
    private MenuItem registerItem = new MenuItem("Register user");
    private MenuItem signInItem = new MenuItem("Sign in");

    //Text fields
    private TextField searchField = new TextField();

    private TextArea textArea = new TextArea();

    //Constructor
    public GuestPrimaryScene(Stage primaryStage) {
        setScene(primaryStage);

        sceneEvents(primaryStage);

        primaryStage.show();
    }

    //upravit do controlleru
    private void setScene(Stage primaryStage) {
        primaryStage.setScene(new Scene(this, primaryStage.getWidth(), primaryStage.getHeight()));

        vBox.getChildren().addAll(searchField, textArea);

        this.getChildren().addAll(menuBar, vBox);
        this.setAlignment(Pos.TOP_LEFT);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        this.setHgap(20);

        //MenuBar
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(findMenu, register);
        findStreetCity.setToggleGroup(findGroup);
        findOwner.setToggleGroup(findGroup);
        findMenu.getItems().addAll(findStreetCity, findOwner);
        findStreetCity.setSelected(true);

        register.getItems().addAll(registerItem, signInItem);

        //VBox - textField, textArea
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(5, 30, 30, 10));
        vBox.prefWidthProperty().bind(primaryStage.widthProperty());
        vBox.prefHeightProperty().bind(primaryStage.heightProperty().subtract(150));

        searchField.setPromptText("Find street or city");
        searchField.setMaxWidth(300);

        textArea.prefHeightProperty().bind(vBox.heightProperty());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("You will see results there, if you will search for them...");
    }

    private void sceneEvents (Stage primaryStage) {
        //Change prompt text, when you select, what are you searching for
        guestController.changePromptText(findMenu, findOwner, findStreetCity, searchField);

        //Register
        guestController.switchRegisterStage(registerItem);

        //Sign in
        guestController.logOut(signInItem, primaryStage);

    }


}