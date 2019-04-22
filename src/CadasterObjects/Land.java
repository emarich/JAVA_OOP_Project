package CadasterObjects;

import Owners.City;
import Owners.Owner;
import Owners.Ownership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Land implements Serializable {
    private int registerNum;
    private String address;
    private int area;
    private TypeLand typeLand;
    //land can have more real estates
    private List<RealEstate> realEstates = new ArrayList<>();
    private boolean haveRE = false;
    private Ownership owner;

    public Land(int regNum, String address, int area, Ownership owner) {
        this.registerNum = regNum;
        this.address = address;
        this.area = area;
        setTypeLand(owner);
        this.owner = owner;
    }

    public Land(){}


    //Getters and Setters-------------------------------------------------------

    public void setRegisterNum(int registerNum) {
        this.registerNum = registerNum;
    }
    public int getRegisterNum() {
        return registerNum;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = Address.setPerfectAddress(address);
    }

    public void setArea(int area) {
        this.area = area;
    }
    public int getArea() {
        return area;
    }

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
