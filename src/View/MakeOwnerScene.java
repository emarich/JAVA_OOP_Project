package View;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import UserObject.User;
import ViewContollers.MakeOwnerController;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MakeOwnerScene extends FlowPane {
    //View controller
    private MakeOwnerController makeOwnerController;

    //For make city scene
    private Label phoneLabel = new Label("Set phone number:");
    private TextField phoneNum = new TextField();
    private Label emailLabel = new Label("Set email:");
    private TextField email = new TextField();

    //For make owner scene
    private Label nameLabel = new Label();
    private TextField name = new TextField();
    private Label genderLabel = new Label();
    private ChoiceBox<String> genderBox =
            new ChoiceBox<>(FXCollections.observableArrayList( "Male", "Female"));
    private Label dateLabel = new Label("Enter birth date:");
    private TextField date = new TextField();
    private Label addressLabel = new Label("Enter mutual address:");
    private TextField address = new TextField();

    private Button makeBtn = new Button("Make");

    public MakeOwnerScene(User user, Stage stage, Database usersDatabase, DataObserver textArea) throws Exception {
        makeOwnerController = new MakeOwnerController(usersDatabase, textArea);

        setScene(stage, user);
    }

    private void setScene(Stage stage, User user) {
        stage.setHeight(550);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        stage.setTitle("Formula");

        if(user.getUserType().toString().equalsIgnoreCase("Citizen")) {
            this.getChildren().addAll(nameLabel, name, genderLabel, genderBox, dateLabel,
                    date, addressLabel, address, makeBtn);
            nameLabel.setText("Enter full name for user "+user.getUsername()+":");
            name.setPromptText(user.getUsername());
            genderLabel.setText("Gender:");
            genderBox.show();
            genderBox.getSelectionModel().selectFirst();
            date.setPromptText("DD.MM.YYYY");
            address.setPromptText("Hlavna 1, 801 01 Bratislava, Slovensko");
            makeOwnerController.btnClicked(stage, makeBtn, user, name, genderBox, date, address);
        } else {
            this.getChildren().addAll(phoneLabel, phoneNum, emailLabel, email, makeBtn);
            makeOwnerController.btnClicked(stage, makeBtn, user, phoneNum, email);
        }

        makeBtn.setPrefWidth(280);
    }
}
