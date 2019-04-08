package Owners;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import CadasterObjects.TypeLand;
import OtherFunctionality.PopUpAlert;
import UserObject.User;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner extends Ownership implements Serializable {
    public class Address implements Serializable {
        private String streetAndNum;
        private String city;
        private String state;

        public void setStreetAndNum(String street) {
            streetAndNum = street;
        }
        public String getStreetAndNum(){
            return streetAndNum;
        }

        public void setCity(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }

        public void setState(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }
    }

    private String name;
    private Date birthDate;
    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
    private Address mutualAddress = new Address();
    //private double money = 100000;

    public Owner(String name, String date, String address){
        setName(name);
        setBirthDate(date);
        setMutualAddress(address);
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

    public void setMutualAddress(String address) {
        String[] outputAddress = new String[3];
        try {
            outputAddress = address.split(",");
            if (outputAddress[0] == null || outputAddress[1] == null || outputAddress[2] == null) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                        "You must enter street, city and state");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.INFORMATION,
                    "Please, write it in this format: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
        }
        mutualAddress.setStreetAndNum(outputAddress[0]);
        mutualAddress.setCity(outputAddress[1]);
        mutualAddress.setState(outputAddress[2]);
        outputAddress = null;
    }
    public Address getMutualAddress() {
        return mutualAddress;
    }

    /*public void setMoney(double money) {
        this.money = money;
    }
    public double getMoney() {
        return money;
    }*/

    //Getters and Setters-------------------------------------------------------

    public void addLand(Land land) {
        if (land.getTypeLand() == TypeLand.PRIVATE) {
            ownedLands.add(land);
        } else {
            land.setTypeLand(TypeLand.PRIVATE);
            PopUpAlert Alert = new PopUpAlert(javafx.scene.control.Alert.AlertType.WARNING, "" +
                    "Type of land was changed due of the type of owner.");
            ownedLands.add(land);
        }
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
                land.getCity().equals(RE.getCity()))  {
                RE.setLand(land);
                land.addRealEstate(RE);
            }
        }
    }

    public void linkLandandRE (RealEstate RE) {
        for (Land l : ownedLands) {
            if (RE.getRegisterNum() == l.getRegisterNum() &&
                RE.getCity().equals(l.getCity())) {
                l.addRealEstate(RE);
                RE.setLand(l);
            }
        }
    }








}
