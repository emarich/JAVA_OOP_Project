package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class UserPrimaryStage extends FlowPane{
    private Button user = new Button("User");
    private Menu userInfoMenu = new Menu("User Info");
    private MenuBar menuBar = new MenuBar();



    public UserPrimaryStage(Stage primaryStage, String username) {
        this.getChildren().addAll(menuBar);

        Scene userScene = new Scene(this, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(userScene);
        primaryStage.show();
        setStage();
    }

    private void setStage() {
        menuBar.getMenus().addAll(userInfoMenu);
        userInfoMenu.getItems().add(new MenuItem("Accout"));
        userInfoMenu.getItems().add(new MenuItem("Property"));
        userInfoMenu.getItems().add(new MenuItem("Log out"));
        userInfoMenu.getItems().add(new MenuItem("Exit"));

        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
    }
}
