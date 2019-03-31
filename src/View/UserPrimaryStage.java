package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

public class UserPrimaryStage extends FlowPane{
    private Button user = new Button("User");
    private Menu userInfoMenu = new Menu("User Info");
    private MenuBar menuBar = new MenuBar();

    public UserPrimaryStage() {
        this.getChildren().addAll(user);
    }
}