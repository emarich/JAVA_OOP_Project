package Owners;

import Offices.Office;

import java.io.Serializable;


/**
 * Typ vlastníka pre administrátorov (úrad). Zabezpečuje, že nehnuteľnosti budú typu "Verejné".
 */
public class City extends Ownership implements Serializable {
    private Office office = new Office();

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
