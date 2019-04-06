package View;

import ViewContollers.GuestController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestPrimaryScene extends FlowPane {
    GuestController guestController = new GuestController();

    //Menu
    private MenuBar menuBar = new MenuBar();

    private Menu findMenu = new Menu("Find...");
    private ToggleGroup findGroup = new ToggleGroup();
    private RadioMenuItem findStreetCity = new RadioMenuItem("street or city");
    private RadioMenuItem findOwner = new RadioMenuItem("owner");

    private Menu register = new Menu("Register");
    private MenuItem registerItem = new MenuItem("Register user");

    //Text fields
    private TextField searchField = new TextField();

    private TextArea textArea = new TextArea();

    //Constructor
    public GuestPrimaryScene(Stage primaryStage) {
        this.getChildren().addAll(menuBar, searchField, textArea);

        Scene guestScene = new Scene(this, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(guestScene);
        setScene(primaryStage);
        primaryStage.show();
    }

    //upravit do controlleru
    private void setScene(Stage primaryStage) {
        this.setAlignment(Pos.TOP_LEFT);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        this.setHgap(20);

        //Menu
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(findMenu, register);
        findStreetCity.setToggleGroup(findGroup);
        findOwner.setToggleGroup(findGroup);
        findMenu.getItems().addAll(findStreetCity, findOwner);
        findStreetCity.setSelected(true);

        findStreetCity.setOnAction((ActionEvent) -> {
            searchField.setPromptText("Find street or city");
        });
        findOwner.setOnAction((ActionEvent) -> {
            searchField.setPromptText("Find owner");
        });

        register.getItems().addAll(registerItem);

        //Search text field
        searchField.setPromptText("Find street or city");
        searchField.setMaxWidth(300);

        //Register
        registerItem.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }


}