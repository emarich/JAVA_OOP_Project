package Owners;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Ownership implements Serializable {
    //Contact
    protected String phoneNumber;
    protected String email;

    //List of lands
    protected boolean haveLand = false;
    protected List<Land> ownedLands = new ArrayList<>();

    //List of real estates
    protected boolean haveRealEstate = false;
    protected List<RealEstate> ownedRE = new ArrayList<>();

    //Getters and Setters-------------------------------------------------------

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

    public void setHaveLand(boolean have) {
        haveLand = have;
    }
    public boolean getHaveLand() {
        return haveLand;
    }

    public void setOwnedLands(List<Land> ownedLands) {
        this.ownedLands = ownedLands;
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

    public void setOwnedRE(List<RealEstate> ownedRE) {
        this.ownedRE = ownedRE;
    }
    public List<RealEstate> getOwnedRE () {return ownedRE;}

    //Getters and Setters-------------------------------------------------------

    public void addLand(Land land) {
        ownedLands.add(land);
    }

    public void addRE(RealEstate realEstate) {
        ownedRE.add(realEstate);
    }


    public void phoneNumberCheck(String phoneNumber) {
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

    public void emailCheck(String usersEmail) {
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
