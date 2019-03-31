package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;


public class LogIn extends FlowPane {

    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    ChoiceBox<String> userTypeBox = new ChoiceBox<>(FXCollections.observableArrayList("User", "Office"));
    private Button login = new Button();

    public LogIn() {
        this.getChildren().addAll(username, password, userTypeBox, login);

        setProperties();

        login.setOnAction(e -> {
            System.out.println(username.getText());
        });
    }

    private void setProperties() {
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
        username.setPromptText("Username");
        password.setPromptText("Password");
        userTypeBox.show();
        userTypeBox.getSelectionModel().selectFirst();
        login.setPrefWidth(280);
        login.setText("Login");
    }
    /*

    private void setPromptText() {
        username.setPromptText("username");
        password.setPromptText("password");
        occupation.setPromptText("ocupation");
    }*/


}
