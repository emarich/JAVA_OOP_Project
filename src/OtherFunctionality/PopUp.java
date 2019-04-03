package OtherFunctionality;


import javafx.scene.control.Alert;

public class PopUp extends  Alert {

    public PopUp(AlertType alertType, String message) {
        super(alertType);
        this.setContentText(message);
        this.showAndWait();
    }
}
