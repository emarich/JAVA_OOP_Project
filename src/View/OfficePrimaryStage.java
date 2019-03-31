package View;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

public class OfficePrimaryStage extends FlowPane {
    Button office = new Button("Office");

    public OfficePrimaryStage() {
        this.getChildren().addAll(office);
    }
}
