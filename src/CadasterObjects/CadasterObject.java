package CadasterObjects;

import MyExceptions.WrongInputException;
import OtherFunctionality.CadastreSearch;
import Owners.Ownership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class CadasterObject implements Serializable {
    protected int registerNum;
    protected String address;
    protected int area;
    protected List<Ownership> owners = new ArrayList<>();
    //protected Ownership owner;

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
        this.owners.add(owner);
        //this.owner = owner;
    }
    public Ownership getOwner() {
        return owners.get(0);
    }

    public List<Ownership> getOwners() {
        return owners;
    }

    //Getters and Setters-------------------------------------------------------

    public String toString() {
        return (this.registerNum+"; "+this.address);
    }
}
