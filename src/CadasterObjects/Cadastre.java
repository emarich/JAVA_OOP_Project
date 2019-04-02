package CadasterObjects;

import Owners.LandOwner;

import java.io.Serializable;

public class Cadastre implements Serializable {
    int updatedDate;

    private int numOfREOwners;
    private int numOfRealEstate;
    private boolean isLandOwner;

    private Land land = new Land();
    //private LandOwner landOwner = new LandOwner();
    private RealEstate realEstate = new RealEstate();









    public void setNumOfREOwners(int numOfREOwners) {
        this.numOfREOwners = numOfREOwners;
    }
    public int getNumOfREOwners() {
        return numOfREOwners;
    }

    public void setNumOfRealEstate(int numOfRealEstate) {
        this.numOfRealEstate = numOfRealEstate;
    }
    public int getNumOfRealEstate() {
        return numOfRealEstate;
    }

    public void setIsLandOwner(boolean isLandOwner) {
        this.isLandOwner = isLandOwner;
    }
    public boolean geetIsLandOwner() {
        return isLandOwner;
    }


}
