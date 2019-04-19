package CadasterObjects;

import Owners.Owner;

import java.io.Serializable;

public class RealEstate implements Serializable {
    private int registerNum;
    private Address address;
    private int area;
    //real estate can be only in one land
    private TypeRealEstate typeRealEstate;
    private Land land;
    private boolean haveLand = false;
    //private Owner owner;


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

    public void setTypeRealEstate(TypeRealEstate typeRealEstate) {
        this.typeRealEstate = typeRealEstate;
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

    //Getters and Setters-------------------------------------------------------
}

