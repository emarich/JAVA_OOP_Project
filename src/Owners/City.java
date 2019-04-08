package Owners;

import CadasterObjects.Land;
import CadasterObjects.TypeLand;
import Offices.CadastralOffice;
import Offices.Office;
import OtherFunctionality.PopUpAlert;

import java.io.Serializable;

public class City extends Ownership implements Serializable {
    private Office office = new CadastralOffice();

    public City(String phoneNum, String email) {
        setPhoneNumber(phoneNum);
        setEmail(email);
    }

    public void setOffice(Office office) {
        this.office = office;
    }
    public Office getOffice() {
        return office;
    }

    public void addLand(Land land) {
        if (land.getTypeLand() == TypeLand.PUBLIC) {
            ownedLands.add(land);
        } else {
            land.setTypeLand(TypeLand.PUBLIC);
            PopUpAlert Alert = new PopUpAlert(javafx.scene.control.Alert.AlertType.WARNING, "" +
                    "Type of land was changed due of the type of owner.");
            ownedLands.add(land);
        }
    }
}
