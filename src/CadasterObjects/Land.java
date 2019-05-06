package CadasterObjects;

import OtherFunctionality.FindSubstring;
import OtherFunctionality.WrongLandformInputException;
import Owners.City;
import Owners.Owner;
import Owners.Ownership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Land extends CadasterObject implements Serializable {
    private TypeLand typeLand;
    private Landform landform;
    //land can have more real estates
    private List<RealEstate> realEstates = new ArrayList<>();
    private boolean haveRE = false;

    public Land(int regNum, String address, int area, Ownership owner, String form) throws WrongLandformInputException {
        this.registerNum = regNum;
        this.address = address;
        this.area = area;
        setTypeLand(owner);
        this.owner = owner;
        setLandform(form);

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

    public void setLandform(String form) throws WrongLandformInputException {
        for (Landform l : Landform.values()) {
            if (FindSubstring.findExact(l.getShortName(), form)) {
                landform = l;
                break;
            }
        }
        if (landform == null) {
            throw new WrongLandformInputException("Error: landform input");
        }
    }
    public Landform getLandform() {
        return landform;
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

    public boolean getHaveRE() {
        return haveRE;
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
