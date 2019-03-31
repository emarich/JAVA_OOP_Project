package CadasterObject;

import java.util.ArrayList;
import java.util.List;

public class REOwner extends Owner {
    Cadastre cadastre = new Cadastre();

    public int numOfREOwners = cadastre.getNumOfREOwners();

    public Owner makeREOwner() {
        if (numOfREOwners == 0) {
            City city = new City();
            return city;
        }
        REOwner REOwner = new REOwner();
        return REOwner;
    }

    /*public Owner makeREOwners(int num) {
        //setNumOfREOwners(num);
        if (num >= 0) {
            if (num == 0) {

            } else if (num == 1) {
                REOwner REOwner = new REOwner();
                return REOwner;
            } else {
                List<REOwner> listOfREOwners = new ArrayList<>();
                //return listOfREOwners;
            }
        } else {
            throw new ArithmeticException("Set number >= 0");
        }
        return REOwner
    }*/
}
