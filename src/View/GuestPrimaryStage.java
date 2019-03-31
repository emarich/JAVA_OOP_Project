package View;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;

public class GuestPrimaryStage extends FlowPane {
    private Button guest = new Button("Guest");



    public GuestPrimaryStage() {
        this.getChildren().addAll(guest);
    }

    private void setLayout() {
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);
    }
}
