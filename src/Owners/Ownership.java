package Owners;

import CadasterObjects.CadasterObject;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import OtherFunctionality.EmailFormatException;
import OtherFunctionality.FindSubstring;
import OtherFunctionality.PhoneNumberFormatException;
import OtherFunctionality.SameRegNumException;
import UserObject.Database;
import UserObject.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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


    //checks, if the phone number is correct
    public static void phoneNumberCheck(String phoneNumber) throws PhoneNumberFormatException {
        char firstChar = phoneNumber.charAt(0);
        if (phoneNumber.matches("[0-9 +]+")) {
            int count = 0;
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (Character.isDigit(phoneNumber.charAt(i))) {
                    count++;
                }
            }
            if (phoneNumber.charAt(0)== '+') {
                count++;
            }

            if (count == 10) {
                if (firstChar != '0') {
                    throw new PhoneNumberFormatException("Invalid format. \"0\" must be first");
                }
            } else if (count == 13) {
                if (firstChar != '+') {
                    throw new PhoneNumberFormatException("Invalid format. \"+\" must be first");
                }
            } else {
                throw new PhoneNumberFormatException("Invalid length");
            }
        } else {
            throw new PhoneNumberFormatException("Invalid characters");
        }
    }
    //checks, if the email is correct
    public static void emailCheck(String usersEmail) throws EmailFormatException {
            int count = 0;

            if(usersEmail.length()<5 || usersEmail.length()>80) {
                throw new EmailFormatException("Invalid length of email");
            }

            if(usersEmail.contains("@")) {
                int a = usersEmail.indexOf("@");

                for(int i=a; i<usersEmail.length(); i++){
                    if(usersEmail.charAt(i)=='.'){
                        count += 1;
                        i = usersEmail.length();
                    }
                }
                if(count == 0){
                    throw new EmailFormatException("Invalid position of dot characters");
                }
            } else {
                throw new EmailFormatException("No \"@\" symbol present");
            }

            if(!(usersEmail.matches("[a-z0-9_]+@.*"))) {
                throw new EmailFormatException("Invalid characters");
            }
    }


    public void existingRegNum(Land land) throws SameRegNumException {
        int seekingNum = land.getRegisterNum();

        if (this.haveLand) {
            for (Land l : this.getOwnedLands()) {
                if (l.getRegisterNum() == seekingNum) {
                    compareAddress(land, l, seekingNum);
                }
            }
        }
    }

    public void existingRegNum(RealEstate realEstate)  throws SameRegNumException{
        int seekingNum = realEstate.getRegisterNum();

        if (this.haveRealEstate) {
            for (RealEstate re : this.getOwnedRE()) {
                if (re.getRegisterNum() == seekingNum) {
                    compareAddress(realEstate, re, seekingNum);
                }
            }
        }
    }

    private void compareAddress(CadasterObject newObject, CadasterObject current, int regNum) throws SameRegNumException {
        String[] outputAddress = new String[3];
        //newObject
        outputAddress = newObject.getAddress().split(",");// get whole address
        String newStreet = outputAddress[0].trim(); // get street
        String newTown = outputAddress[1].trim(); // get town
        //current
        outputAddress = current.getAddress().split(","); // get whole address
        String currentStreet = outputAddress[0].trim(); // get street
        String currentTown = outputAddress[1].trim(); // get town

        if (newObject instanceof Land && current instanceof RealEstate) {
            if (FindSubstring.findExact(newStreet, currentStreet) &&
                    FindSubstring.findExact(newTown, currentTown)) {
                ((Land) newObject).addRealEstate((RealEstate) current);
                System.out.println("LAND has new real estate.");
            }
        } else if (newObject instanceof RealEstate && current instanceof Land) {
            if (FindSubstring.findExact(newStreet, currentStreet) &&
                    FindSubstring.findExact(newTown, currentTown)) {
                ((RealEstate) newObject).addLand((Land) current);
                System.out.println("REAL ESTATE has new land.");
            }
        }

        if (newObject instanceof Land && current instanceof Land) {
            if (FindSubstring.findExact(newTown, currentTown)) {
                throw new SameRegNumException("Land: "+regNum+" already exists in "+newTown);
            }
        } else if (newObject instanceof RealEstate && current instanceof RealEstate) {
            if (FindSubstring.findExact(newTown, currentTown)) {
                throw new SameRegNumException("Real estate: "+regNum+" already exists in "+newTown);
            }
        } else {
            System.out.println("Cadastre objects are different");
        }
    }

    public void compareRegNum(CadasterObject object, Database database)  throws SameRegNumException{
        int seekingNum = object.getRegisterNum(); //actual reg number of object
        String[] inputAddress = object.getAddress().split(","); //actual address of object

        User user;
        Ownership owner;

        for (String u : database.getUsersDataHM().keySet()) {
            user = database.getUser(u); //get user

            if (user.getIsOwner()) {
                owner = user.getOwner();

                    if (owner.getHaveLand()) {
                        for (Land l : owner.getOwnedLands()) { //get users lands
                            if (l.getRegisterNum() == seekingNum) {
                                compareAddress(object, l, seekingNum); //throws error
                            }
                        }
                    }

                    if (owner.getHaveRealEstate()) {
                        for (RealEstate re : owner.getOwnedRE()) { //get users lands
                            if (re.getRegisterNum() == seekingNum) {
                                compareAddress(object, re, seekingNum); //throws error
                            }
                        }
                    }

            }

        }


    }

}
