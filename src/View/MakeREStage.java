package View;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import UserObject.User;
import ViewContollers.MakeLandController;
import ViewContollers.MakeREController;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MakeREStage extends FlowPane {
    //View controller
    private MakeREController makeREController;

    //New stage
    private Stage stage = new Stage();

    //For make owner scene
    private Label regNumLabel = new Label("Enter register number of real estate:");
    private TextField regNum = new TextField();
    private Label addressLabel = new Label("Enter address:");
    private TextField address = new TextField();
    private Label areaLabel = new Label("Enter area:");
    private TextField area = new TextField();
    private Label typeRE = new Label("Choose type:");
    private ChoiceBox<String> realEstateTypeBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "Residential", "Commercial", "Industrial", "Farm"));

    private Button makeBtn = new Button("Make");

    public MakeREStage(User user, Database usersDatabase, DataObserver textArea) throws Exception {
        makeREController = new MakeREController(user, usersDatabase);

        setScene(stage, user);

        sceneEvents();

        stage.show();
    }

    private void setScene(Stage stage, User user) {
        stage.setTitle("Make real estate for "+user.getUsername());
        stage.setWidth(400);
        stage.setHeight(550);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        this.getChildren().addAll(regNumLabel, regNum, addressLabel, address,
                areaLabel, area, typeRE, realEstateTypeBox, makeBtn);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        regNum.setPromptText("Register number");
        address.setPromptText("Format:  Hlavna 1, Bratislava, Slovensko");
        area.setPromptText("Area (m^2)");

        makeBtn.setPrefWidth(280);
    }

    public void sceneEvents () {
        makeBtn.setOnAction(event -> {
            makeREController.makeLandClicked(makeBtn, regNum, address, area, realEstateTypeBox, stage);
        });
    }
}

