package View;

import OtherFunctionality.DataObserver;
import UserObject.Database;
import UserObject.User;
import ViewContollers.MakeLandController;
import ViewContollers.NewRequestController;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewRequestStage extends FlowPane {
    //View controller
    private NewRequestController newRequestController;

    //New stage
    private Stage stage = new Stage();

    private VBox vBox = new VBox();

    private Label officeLabel = new Label("To office:");
    private ChoiceBox<String> officeBox = new ChoiceBox<>();
    private Text textDeposit = new Text(10, 20, "Deposit to cadastre");

    private Label proposersLabel = new Label("Proposers:");
    private TextField proposer1 = new TextField("Name Surname");
    private TextField proposer2 = new TextField("Name Surname");

    private Label favorLabel = new Label("In favor of:");
    private TextField nameTextfield = new TextField();

    private Label requestTypeLabel = new Label("Type of request: ");
    private ChoiceBox<String> requestBox =
            new ChoiceBox<>(FXCollections.observableArrayList(
                    "Arable land", "Hop field", "Vineyard",
                    "Garden", "Fruit grove", "Grass field",
                    "Woods", "Water area", "Built-up area", "Other"));

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private LocalDate localDate = LocalDate.now();

    private Text footer = new Text("Date: "+dtf.format(localDate));


    private Button makeBtn = new Button("Make");

    public NewRequestStage(User user, Database usersDatabase) throws Exception {
        newRequestController = new NewRequestController(user, usersDatabase);

        setScene(stage, user);

        sceneEvents();

        stage.show();
    }

    private void setScene(Stage stage, User user) {
        stage.setTitle("Request from "+user.getUsername());
        stage.setWidth(400);
        stage.setHeight(700);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        vBox.getChildren().addAll(officeLabel, officeBox, textDeposit, proposersLabel, proposer1, proposer2,
                favorLabel, nameTextfield, requestTypeLabel, requestBox, footer, makeBtn);
        vBox.setSpacing(20);

        this.getChildren().addAll(vBox);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        officeBox.setPrefWidth(280);
        textDeposit.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        makeBtn.setPrefWidth(280);
    }

    private void sceneEvents () {

    }
}
