package Offices;

import Owners.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadastralOffice extends Office {

    private List<Owner> owners = new ArrayList<>();



    public List<Owner> registeNewUser(String username, String fullName, String birthDate, String address) {
        owners.add(new Owner(username, fullName, birthDate, address));
        return owners;
    }

    //public List makeUserLandOwner() { }

    public Owner findUser(String username) {
        for (Owner o : owners) {
            if (o.getUsername() == username)  {
                return o;
            }
        }
        System.out.println("Invalid username.");
        return null;
    }


}