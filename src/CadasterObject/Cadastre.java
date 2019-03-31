package CadasterObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cadastre implements Serializable {
    int updatedDate;

    private int numOfREOwners;
    private int numOfRealEstate;
    private boolean isLandOwner;

    Land land = new Land();
    LandOwner landOwner = new LandOwner();









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
