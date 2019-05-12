package Owners;

import CadasterObjects.Land;
import CadasterObjects.TypeLand;
import Offices.CadastralOffice;
import Offices.Office;

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
}
