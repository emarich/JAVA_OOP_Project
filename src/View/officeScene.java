package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class officeScene {

    public officeScene(Stage PrimaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/officeScene.fxml"));
        Scene scene = new Scene(root);

        PrimaryStage.setScene(scene);
        PrimaryStage.show();

    }
    public static String getScene() {
        return "OfficeScene";
    }
}
