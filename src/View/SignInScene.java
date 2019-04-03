package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SignInScene extends FlowPane {

    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private ChoiceBox<String> userTypeBox = new ChoiceBox<>(FXCollections.observableArrayList("Guest", "User", "Office"));
    private Button signIn = new Button();

    public SignInScene(Stage primaryStage) {
        this.getChildren().addAll(username, password, userTypeBox, signIn);

        setStage();

        //Stage stage = new Stage();



        signIn.setOnAction(e -> {

            if (userTypeBox.getValue().equals("Guest")) {
                GuestPrimaryScene guestStage = new GuestPrimaryScene(primaryStage);
                Scene guestScene = new Scene(guestStage, 500, 500);
                primaryStage.setScene(guestScene);
                primaryStage.show();
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
                        UserPrimaryStage userStage = new UserPrimaryStage(primaryStage);
                        Scene userScene = new Scene(userStage, 500 , 500);
                        primaryStage.setScene(userScene);
                        primaryStage.show();
                        break;
                    case "Office":
                        System.out.println("Office");
                        OfficePrimaryStage officeStage = new OfficePrimaryStage(primaryStage);
                        Scene officeScene = new Scene(officeStage, 500, 500);
                        primaryStage.setScene(officeScene);
                        primaryStage.show();
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

        signIn.setText("Sign In");
        signIn.setPrefWidth(280);

    }




}

