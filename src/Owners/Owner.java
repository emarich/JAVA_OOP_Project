package Owners;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import OtherFunctionality.PopUp;
import UserObject.User;
import javafx.scene.control.Alert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner extends User {
    public class Address {
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
    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yy");
    private Address mutualAddress = new Address();
    private String phoneNumber;
    private String email;
    private double money = 100000;

    private boolean haveLand = false;
    private List<Land> ownedLands = new ArrayList<>();
    private boolean haveRealEstate = false;
    private List<RealEstate> ownedRE = new ArrayList<>();
    public Owner(String name, String date, String address){
        setName(name);
        setBirthDate(date);
        setMutualAddress(address);
    }

    public Owner() {}


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
        } catch (ArrayIndexOutOfBoundsException e) {
            PopUp alert = new PopUp(Alert.AlertType.INFORMATION,
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public double getMoney() {
        return money;
    }

    public void setHaveLand(boolean have) {
        haveLand = have;
    }
    public boolean getHaveLand() {
        return haveLand;
    }

    public void addLand(Land land) {
        ownedLands.add(land);
    }
    public List<Land> getOwnedLands() {
        return ownedLands;
    }

    public void setHaveRealEstate(boolean have) {
        haveRealEstate = have;
    }
    public boolean getHaveRealEstate() {
        return haveRealEstate;
    }

    public void addRE (RealEstate realEstate) {
        ownedRE.add(realEstate);
    }
    public List<RealEstate> getOwnedRE () {return ownedRE;};

    //Getters and Setters-------------------------------------------------------


    public void linkLandandRE (Land land) {
        
    }









    private void phoneNumberCheck(String phoneNumber) {
        if (phoneNumber.matches("[0-9 ]+")) {
            char firstChar = phoneNumber.charAt(0);

            if (phoneNumber.length() == 10) {
                if (firstChar != '0') {
                    System.out.println("Invalid format. \"0\" must be first");
                }
            } else if (phoneNumber.length() == 13) {
                if (firstChar != '+') {
                    System.out.println("Invalid format. \"+\" must be first");
                }
            } else {
                System.out.println("Invalid length");
            }
        } else {
            System.out.println("Invalid characters");
        }
    }

    private void emailCheck(String usersEmail) {
        int count = 0;

        if(!(usersEmail.length()>3 && usersEmail.length()<40)) {
            System.out.println("Inavlid length of email");
        }

        if(email.contains("@")){
            int a = email.indexOf("@");

            for(int i=a; i<usersEmail.length(); i++){
                if(email.charAt(i)=='.'){
                    count += 1;
                }
            }
            if(count != 1){
                System.out.println("Invalid position of special characters");
            }
        } else{
            System.out.println("No \"@\" symbol present");
        }

        if(!(email.matches("[a-z _]+@.*"))) {
            System.out.println("Invalid characters");
        }

    }
}
