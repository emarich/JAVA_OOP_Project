package View;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class OfficePrimaryStage extends FlowPane {
    private MenuBar menuBar = new MenuBar();

    private Menu citizens = new Menu("Citizens");
    private MenuItem addUserItem = new MenuItem("Add User");
    private Menu makeOwner = new Menu("Make owner from user");
    private MenuItem landOwnerItem = new MenuItem("Make land owner");
    private MenuItem REOwnerItem = new MenuItem("Make real etsate owner");

    private Menu office = new Menu("Office");
    private MenuItem requestItem = new MenuItem("Requests");

    private Menu land = new Menu("Land");
    private Menu realEstate = new Menu("Real Estates");



    public OfficePrimaryStage(Stage primaryStage) {
        this.getChildren().addAll(menuBar);

        setScene(primaryStage);

        //addUserItem.setOnAction(event -> {

        //});


    }

    private void setScene(Stage primaryStage) {
        this.setAlignment(Pos.TOP_LEFT);
        this.menuBar.setPrefWidth(primaryStage.getWidth());
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        menuBar.getMenus().add(citizens);
        menuBar.getMenus().add(office);

        citizens.getItems().add(addUserItem);
        citizens.getItems().add(makeOwner);
        makeOwner.getItems().add(landOwnerItem);
        makeOwner.getItems().add(REOwnerItem);

        office.getItems().add(requestItem);

        //menuBar.prefWidthProperty().bind(stage.widthProperty());

    }
}
