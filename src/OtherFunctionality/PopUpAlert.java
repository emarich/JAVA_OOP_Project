package OtherFunctionality;


import javafx.scene.control.Alert;

public class PopUpAlert extends  Alert {

    public PopUpAlert(AlertType alertType, String message) {
        super(alertType);
        this.setContentText(message);
        this.showAndWait();
    }
}
