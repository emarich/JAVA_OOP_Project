package View;

import OtherFunctionality.PrintCadastre;
import UserObject.User;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class UserPropertyStage extends FlowPane {
    private Stage stage = new Stage();
    private TextArea textArea = new TextArea();
    private User user;
    private HBox hBox = new HBox();

    public UserPropertyStage(User user) throws Exception {
        this.user = user;

        setScene();

        sceneEvents();

        stage.show();
    }

    private void setScene() {
        stage.setTitle("Property");
        stage.setWidth(450);
        stage.setHeight(400);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        hBox.getChildren().add(textArea);
        hBox.setPadding(new Insets(5, 30, 30, 30));
        hBox.prefWidthProperty().bind(stage.widthProperty());
        hBox.prefHeightProperty().bind(stage.heightProperty().subtract(100));

        //textArea.prefHeightProperty().bind(hBox.heightProperty());
        textArea.prefWidthProperty().bind(hBox.widthProperty());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        this.getChildren().addAll(hBox);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
    }

    private void sceneEvents() {
        PrintCadastre printCadastre = new PrintCadastre(user, textArea);
        printCadastre.printUserProperty();
    }
}
