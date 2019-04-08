package View;

import ViewContollers.CheckUserController;
import ViewContollers.MakeOwnerController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CheckUserStage extends FlowPane {
    //View controller
    private CheckUserController checkUserController = new CheckUserController();

    //New stage
    private Stage stage = new Stage();

    private Label usernameLabel = new Label("Enter a username:");
    private TextField username = new TextField();
    private Button checkButton = new Button("Check user");

    public CheckUserStage() throws Exception {
        setScene(stage);

        sceneEvents();

        stage.show();
    }

    private void setScene(Stage stage) {
        stage.setTitle("Registration formula");
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        this.getChildren().addAll(usernameLabel, username, checkButton);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        username.setPromptText("Username");

        checkButton.setPrefWidth(280);
    }

    public void sceneEvents () {
        checkUserController.checkUser(username, checkButton, stage);
    }
}