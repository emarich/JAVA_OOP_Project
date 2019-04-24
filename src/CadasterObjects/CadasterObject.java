package CadasterObjects;

import Owners.Ownership;

public abstract class CadasterObject {
    protected int registerNum;
    protected String address;
    protected int area;
    protected Ownership owner;

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

    public void setOwner(Ownership owner) {
        this.owner = owner;
    }
    public Ownership getOwner() {
        return owner;
    }
    //Getters and Setters-------------------------------------------------------
}
