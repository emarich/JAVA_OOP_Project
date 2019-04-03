package CadasterObjects;

import Owners.Owner;

public class RealEstate {
    private int registerNum;
    private String city;
    private int area;
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

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
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

