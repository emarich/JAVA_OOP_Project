package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LogIn extends FlowPane {

    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private ChoiceBox<String> userTypeBox = new ChoiceBox<>(FXCollections.observableArrayList("Guest", "User", "Office"));
    private Button login = new Button();

    public LogIn() {
        this.getChildren().addAll(username, password, userTypeBox, login);

        setStage();

        Stage stage = new Stage();



        login.setOnAction(e -> {

            if (userTypeBox.getValue().equals("Guest")) {
                GuestPrimaryStage guestStage = new GuestPrimaryStage();
                Scene guestScene = new Scene(guestStage, 500, 500);
                stage.setScene(guestScene);
                stage.show();
            } else {
                //if((username.getText() == null || username.getText().trim().isEmpty()) || (password.getText() == null || password.getText().trim().isEmpty())) {
                    /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Missing username or password");
                    alert.setHeaderText("Username and/or password must be filled");
                    alert.showAndWait();*/
                //} else {
                    switch (userTypeBox.getValue()) {
                        case "User":
                            System.out.println("User");
                            UserPrimaryStage userStage = new UserPrimaryStage();
                            Scene userScene = new Scene(userStage, 500 , 500);
                            stage.setScene(userScene);
                            stage.show();
                            break;
                        case "Office":
                            System.out.println("Office");
                            OfficePrimaryStage officeStage = new OfficePrimaryStage();
                            Scene officeScene = new Scene(officeStage, 500, 500);
                            stage.setScene(officeScene);
                            stage.show();
                            break;
                    }
                //}
            }


        });
    }

    private void setStage() {
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        username.setPromptText("Username");

        password.setPromptText("Password");

        userTypeBox.show();
        userTypeBox.getSelectionModel().selectFirst();

        login.setText("Sign In");
        login.setPrefWidth(280);

    }




}
