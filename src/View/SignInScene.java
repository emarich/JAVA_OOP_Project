package View;

import OtherFunctionality.PopUpAlert;
import OtherFunctionality.SerializableUtility;
import UserObject.Database;
import ViewContollers.SignInController;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class SignInScene extends FlowPane {
    private Database usersDatabase = new Database();

    //View controller
    private SignInController signInController;

    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private ChoiceBox<String> userTypeBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "Citizen", "Office", "Guest"));
    private Button signIn = new Button();
    private Button registerBtn = new Button();

    //Constructor
    public SignInScene(Stage primaryStage, Database usersDatabase) throws Exception {

        signInController = new SignInController(usersDatabase);

        //Setting scene
        setScene(primaryStage);

        //Actions on scene
        sceneEvents(primaryStage);

        primaryStage.show();

        checkDatabase(usersDatabase);
    }

    private void setScene(Stage primaryStage) {
        primaryStage.setScene(new Scene(this, primaryStage.getWidth(), primaryStage.getHeight()));

        this.getChildren().addAll(username, password, userTypeBox,
                signIn, registerBtn);
        this.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        username.setPromptText("Username");

        password.setPromptText("Password");

        userTypeBox.show();
        userTypeBox.getSelectionModel().selectFirst();

        signIn.setText("Sign In");
        signIn.setPrefWidth(280);

        registerBtn.setText("Register");
    }

    private void sceneEvents (Stage primaryStage) {
        //Automatically switch to Guest scene, if "Guest" is selected
        signInController.checkGuest(userTypeBox, primaryStage);

        //Sign In button event
        signInController.buttonClicked(signIn, username, password, userTypeBox, primaryStage);

        //Switch to register stage
        signInController.switchRegisterStage(registerBtn);
    }

    private void checkDatabase(Database usersDatabase) {
        if (usersDatabase.getUsersDataHM().isEmpty()) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR,
                    "Users not found." +
                            "\nPlease register.");
        } else {
            usersDatabase.printUsers();
        }
    }
}


