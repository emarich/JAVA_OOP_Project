package View;

import ViewContollers.MakeLandController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MakeLandStage extends FlowPane {
    //View controller
    private MakeLandController makeLandController = new MakeLandController();

    //New stage
    private Stage stage = new Stage();

    private Label usernameLabel = new Label("This land will be for user:");
    private TextField username = new TextField();

    //For make owner scene
    private Label regNumLabel = new Label("Enter register number of land:");
    private TextField regNum = new TextField();
    private Label cityLabel = new Label("Enter city:");
    private TextField city = new TextField();
    private Label areaLabel = new Label("Enter area:");
    private TextField area = new TextField();

    private Button makeBtn = new Button("Make");
    private Button checkBtn = new Button("Check");

    public MakeLandStage() throws Exception {
        setScene(stage);

        sceneEvents();

        stage.show();
    }

    private void setScene(Stage stage) {
        stage.setTitle("Make land");
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        this.getChildren().addAll(usernameLabel, username, checkBtn, regNumLabel, regNum, cityLabel, city,
                areaLabel, area, makeBtn);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        username.setPromptText("Username");
        regNum.setPromptText("Register number");
        city.setPromptText("City");
        area.setPromptText("Area (m^2)");

        makeBtn.setPrefWidth(280);
    }

    public void sceneEvents () {
        makeLandController.checkUserIfIsOwner(checkBtn, username, stage);

        makeLandController.makeLandClicked(makeBtn, username, regNum, city, area, stage);

    }
}
