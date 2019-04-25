package CadasterObjects;

import Owners.City;
import Owners.Owner;
import Owners.Ownership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Land extends CadasterObject implements Serializable {
    private TypeLand typeLand;
    //land can have more real estates
    private List<RealEstate> realEstates = new ArrayList<>();
    private boolean haveRE = false;

    public Land(int regNum, String address, int area, Ownership owner) {
        this.registerNum = regNum;
        this.address = address;
        this.area = area;
        setTypeLand(owner);
        this.owner = owner;
    }

    public Land(){}


    //Getters and Setters-------------------------------------------------------

    public void setTypeLand(Ownership owner) {
        if(owner instanceof City) {
            typeLand = TypeLand.PUBLIC;
        } else if (owner instanceof Owner){
            typeLand = TypeLand.PRIVATE;
        } else {
            typeLand = TypeLand.PUBLIC;
        }
    }
    public TypeLand getTypeLand() {
        return typeLand;
    }

    public void setRealEstates(List<RealEstate> realEstates) {
        this.realEstates = realEstates; }
    public List<RealEstate> getRealEstates() {
        return realEstates;
    }

    public void setOwner(Ownership owner) {
        this.owner = owner;
    }
    public Ownership getOwner() {
        return owner;
    }
    //Getters and Setters-------------------------------------------------------

    public void addRealEstate(RealEstate realEstate) {
        if (realEstate.getRegisterNum() == registerNum) { //if register numbers are same
            if (this.address.equalsIgnoreCase(realEstate.getAddress())) {
                realEstates.add(realEstate);
                haveRE = true;
            }
        }
    }
}
