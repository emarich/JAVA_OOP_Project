package CadasterObjects;

import Owners.City;
import Owners.Owner;
import Owners.Ownership;

import java.io.Serializable;

public class RealEstate implements Serializable {
    private int registerNum;
    private Address address;
    private int area;
    //real estate can be only in one land
    private TypeRealEstate typeRealEstate;
    private Land land;
    private boolean haveLand = false;
    private Ownership owner;

    public RealEstate(int regNum, String address, int area, Ownership owner, String typeRE) {
        setRegisterNum(regNum);
        this.address.setAddress(address);
        setArea(area);
        setTypeRealEstate(typeRE);
    }

    public RealEstate(){}


    //Getters and Setters-------------------------------------------------------

    public void setRegisterNum(int registerNum) {
        this.registerNum = registerNum;
    }
    public int getRegisterNum() {
        return registerNum;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public void setArea(int area) {
        this.area = area;
    }
    public int getArea() {
        return area;
    }

    public void setTypeRealEstate(String type) {
        if (type.equalsIgnoreCase(TypeRealEstate.RESIDENTIAL.toString())) {
            typeRealEstate = TypeRealEstate.RESIDENTIAL;
        } else if (type.equalsIgnoreCase(TypeRealEstate.COMMERCIAL.toString())) {
            typeRealEstate = TypeRealEstate.COMMERCIAL;
        } else if (type.equalsIgnoreCase(TypeRealEstate.INDUSTRIAL.toString())) {
            typeRealEstate = TypeRealEstate.INDUSTRIAL;
        } else if (type.equalsIgnoreCase(TypeRealEstate.FARM.toString())) {
            typeRealEstate = TypeRealEstate.FARM;
        } else {
            typeRealEstate = TypeRealEstate.RESIDENTIAL;
        }
    }
    public TypeRealEstate getTypeRealEstate() {
        return typeRealEstate;
    }

    public void setLand(Land land) {
        this.land = land;
    }
    public Land getLand() {
        return land;
    }

    public void setHaveLand(boolean haveLand) {
        this.haveLand = haveLand;
    }
    public boolean getHaveLand() {
        return haveLand;
    }

    public void setOwner(Ownership owner) {
        this.owner = owner;
    }

    public Ownership getOwner() {
        return owner;
    }

    //Getters and Setters-------------------------------------------------------
}

