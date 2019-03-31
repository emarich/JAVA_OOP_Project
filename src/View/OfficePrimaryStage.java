package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class OfficePrimaryStage extends BorderPane { //skusit iny pane
    private MenuBar menuBar = new MenuBar();
    private Menu citizens = new Menu("Citizens");
    private Menu office = new Menu("Office");
    private Stage stage = new Stage();


    public OfficePrimaryStage() {
        this.getChildren().addAll(menuBar);

        setStage();
    }

    private void setStage() {
        this.setTop(menuBar);

        menuBar.getMenus().add(citizens);
        menuBar.getMenus().add(office);
        citizens.getItems().add(new MenuItem("App Users"));
        citizens.getItems().add(new MenuItem("Office"));

        //menuBar.prefWidthProperty().bind(stage.widthProperty());

    }
}
