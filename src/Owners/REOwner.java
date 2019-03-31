package Owners;

import CadasterObjects.Cadastre;

public class REOwner extends Owner {
    Cadastre cadastre = new Cadastre();

    private int numOfREOwners = cadastre.getNumOfREOwners();

    public Owner registerREOwner() {
        if (numOfREOwners == 0) {
            City city = new City();
            return city;
        }
        REOwner REOwner = new REOwner();
        return REOwner;
    }

   // public Owner addREOwner() {

}
