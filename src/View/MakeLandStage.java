package View;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import UserObject.User;
import ViewContollers.MakeLandController;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MakeLandStage extends FlowPane {
    //View controller
    private MakeLandController makeLandController;

    //New stage
    private Stage stage = new Stage();

    //For make owner scene
    private Label regNumLabel = new Label("Enter register number of land:");
    private TextField regNum = new TextField();
    private Label addressLabel = new Label("Enter address:");
    private TextField address = new TextField();
    private Label areaLabel = new Label("Enter area:");
    private TextField area = new TextField();
    private Label typeLand = new Label("Choose type:");
    private ChoiceBox<String> landTypeBox =
            new ChoiceBox<>(FXCollections.observableArrayList(
                    "Arable land", "Hop field", "Vineyard",
                    "Garden", "Fruit grove", "Grass field",
                    "Woods", "Water area", "Built-up area", "Other"));


    private Button makeBtn = new Button("Make");

    public MakeLandStage(User user, Database usersDatabase, DataObserver textArea) throws Exception {
        makeLandController = new MakeLandController(user, usersDatabase, textArea);

        setScene(stage, user);

        sceneEvents();

        stage.show();
    }

    private void setScene(Stage stage, User user) {
        stage.setTitle("Make land for user"+user.getUsername());
        stage.setWidth(400);
        stage.setHeight(550);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        this.getChildren().addAll(regNumLabel, regNum, addressLabel, address,
                areaLabel, area, typeLand, landTypeBox, makeBtn);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        regNum.setPromptText("Register number");
        address.setPromptText("Format:  Hlavna 1, Bratislava, Slovensko");
        area.setPromptText("Area (m^2)");

        makeBtn.setPrefWidth(280);
    }

    private void sceneEvents () {
        makeBtn.setOnAction(event -> {
            makeLandController.makeLandClicked(regNum, address, area, landTypeBox, stage);
        });
    }
}
