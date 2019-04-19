package View;

import UserObject.Database;
import UserObject.User;
import ViewContollers.MakeLandController;
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

    private Label usernameLabel = new Label("Check user:");
    private TextField username = new TextField();

    //For make owner scene
    private Label regNumLabel = new Label("Enter register number of land:");
    private TextField regNum = new TextField();
    private Label addressLabel = new Label("Enter address:");
    private TextField address = new TextField();
    private Label areaLabel = new Label("Enter area:");
    private TextField area = new TextField();

    private Button makeBtn = new Button("Make");
    private Button checkBtn = new Button("Check");

    public MakeLandStage(User user, Database usersDatabase) throws Exception {
        makeLandController = new MakeLandController(user, usersDatabase);

        setScene(stage);

        sceneEvents();

        stage.show();
    }

    private void setScene(Stage stage) {
        stage.setTitle("Make land");
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        this.getChildren().addAll(usernameLabel, username, checkBtn, regNumLabel, regNum, addressLabel, address,
                areaLabel, area, makeBtn);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        username.setPromptText("Username");
        regNum.setPromptText("Register number");
        address.setPromptText("Format:  Hlavna 1, 801 01 Bratislava, Slovensko");
        area.setPromptText("Area (m^2)");

        makeBtn.setPrefWidth(280);
    }

    public void sceneEvents () {

        makeBtn.setOnAction(event -> {
            makeLandController.makeLandClicked(makeBtn, username.getText(), regNum, address, area, stage);
        });
    }
}
