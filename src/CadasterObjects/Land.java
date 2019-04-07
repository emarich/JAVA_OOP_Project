package CadasterObjects;

import Owners.Owner;
import Owners.Ownership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Land implements Serializable {
    private int registerNum;
    private String city;
    private int area;
    private TypeLand typeLand;
    private List<RealEstate> realEstates = new ArrayList<>();
    private boolean haveRE = false;
    private Ownership owner;

    public Land(int regNum, String city, int area, String typeLand) {
        setRegisterNum(regNum);
        setCity(city);
        setArea(area);
        setTypeLand(TypeLand.valueOf(typeLand));
    }

    public Land(){}


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

    public void setTypeLand(TypeLand typeLand) {
        this.typeLand = typeLand;
    }
    public TypeLand getTypeLand() {
        return typeLand;
    }

    /*public void setRealEstates(List<RealEstate> realEstates) {
        this.realEstates = realEstates; } */
    public List<RealEstate> getRealEstates() {
        return realEstates;
    }

    //Getters and Setters-------------------------------------------------------

    public void addRealEstate(RealEstate realEstate) {
        if (realEstate.getRegisterNum() == registerNum &&
            realEstate.getCity().equalsIgnoreCase(city)) {
            realEstates.add(realEstate);
        }
    }
}
