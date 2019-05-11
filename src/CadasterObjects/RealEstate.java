package CadasterObjects;

import Offices.CadastralOffice;
import Owners.City;
import Owners.Owner;
import Owners.Ownership;

import java.io.Serializable;

public class RealEstate extends CadasterObject implements Serializable {
    private TypeRealEstate typeRealEstate;
    //real estate can be only on one land
    private Land land;
    private boolean haveLand = false;

    public RealEstate(int regNum, String address, int area, Ownership owner, String typeRE) {
        this.registerNum = regNum;
        this.address = address;
        this.area = area;
        setTypeRealEstate(typeRE);
        setOwner(owner);
    }

    public RealEstate(){}

    //Getters and Setters-------------------------------------------------------

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

    //Getters and Setters-------------------------------------------------------

    public void addLand(Land land) {
        if (land.getRegisterNum() == registerNum/10) { //if register numbers are same
            if (this.address.equalsIgnoreCase(land.getAddress())) {
                    this.land = land;
                    haveLand = true;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+", "+this.typeRealEstate.toString();
    }
}

