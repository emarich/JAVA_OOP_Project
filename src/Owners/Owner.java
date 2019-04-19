package Owners;

import CadasterObjects.Address;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import CadasterObjects.TypeLand;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Owner extends Ownership implements Serializable {


    private String name;
    private Date birthDate;
    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
    private Address mutualAddress = new Address();
    //private double money = 100000;

    public Owner(String name, String date, String address){
        setName(name);
        setBirthDate(date);
        mutualAddress.setAddress(address);
    }

    public Owner() {
    }

    //Getters and Setters-------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setBirthDate(String date) {
        try {
            birthDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getBirthDate() {
        return dateFormat.format(birthDate);
    }

    public Address getMutualAddress() {
        return mutualAddress;
    }
    public void setMutualAddress(Address address) {
        this.mutualAddress = address;
    }

    /*public void setMoney(double money) {
        this.money = money;
    }
    public double getMoney() {
        return money;
    }*/

    //Getters and Setters-------------------------------------------------------
    //POUZIT INSTANCE OF
    @Override
    public void addLand(Land land) {
            ownedLands.add(land);
    }

    public void linkLandandRE (boolean haveRealEstate, Land land) {
        if (haveRealEstate) {
            linkLandandRE(land);
        } else {
            System.out.println(getName()+" aren't owning real estate");
        }
    }

    public void linkLandandRE (boolean haveLand, RealEstate RE) {
        if (haveLand) {
            linkLandandRE(RE);
        } else {
            System.out.println(getName()+" aren't owning land");
        }
    }

    public void linkLandandRE (Land land) {
        for (RealEstate RE : ownedRE) {
            if (land.getRegisterNum() == RE.getRegisterNum() &&
                land.getAddress().getCity().equalsIgnoreCase(RE.getAddress().getCity()))  {
                RE.setLand(land);
                land.addRealEstate(RE);
            }
        }
    }

    public void linkLandandRE (RealEstate RE) {
        for (Land l : ownedLands) {
            if (RE.getRegisterNum() == l.getRegisterNum() &&
                RE.getAddress().getCity().equalsIgnoreCase(l.getAddress().getCity())) {
                l.addRealEstate(RE);
                RE.setLand(l);
            }
        }
    }
}
