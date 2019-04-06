package View;

import ViewContollers.RegisterController;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RegisterStage extends FlowPane {
    //View controller
    private RegisterController registerController = new RegisterController();

    //New stage
    private Stage register = new Stage();

    //Stuff on scene
    private Label usernameLabel = new Label("Set username");
    private TextField username = new TextField();
    private Label passwordLabel = new Label("Set password");
    private PasswordField password = new PasswordField();
    private ChoiceBox<String> userTypeBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "User", "Office"));
    private Button registerBtn = new Button();

    public RegisterStage() throws Exception {
        setScene(register);

        sceneEvents();

        register.show();
    }

    private void setScene(Stage register) {
        register.setTitle("Registration formula");
        register.setWidth(400);
        register.setHeight(400);
        register.setScene(new Scene(this, register.getWidth(), register.getHeight()));

        this.getChildren().addAll(usernameLabel, username, passwordLabel, password,
                userTypeBox, registerBtn);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        username.setPromptText("Username");
        password.setPromptText("Password");

        userTypeBox.show();
        userTypeBox.getSelectionModel().selectFirst();

        registerBtn.setText("Register");
        registerBtn.setPrefWidth(280);
    }

    public void sceneEvents () {

        registerController.registerUser(register, registerBtn, username, password,
                userTypeBox);
    }
}
