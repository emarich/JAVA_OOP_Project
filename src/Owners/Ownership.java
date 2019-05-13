package Owners;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import MyExceptions.EmailFormatException;
import MyExceptions.PhoneNumberFormatException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Nadradená trieda triedam {@link Owner} a {@link City}.
 * Ukladá vo svojej ínštancii list {@code land} objektov a list {@code realEstate} objektov a informáciu, čí dané typy
 * nehnuteľností vlastní alebo nie viď {@link #ownedLands} a {@link #ownedRE}
 */
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
        if (ownedLands.size() > 1) {
            haveLand = true;
        }
    }
    public void deleteLand(Land land) {
        ownedLands.remove(land);
        if (ownedLands.size() == 0) {
            haveLand = false;
        }
    }

    public void addRE(RealEstate realEstate) {
        ownedRE.add(realEstate);
        if (ownedRE.size() > 1) {
            haveRealEstate = true;
        }
    }
    public void deleteRE(RealEstate realEstate) {
        ownedRE.remove(realEstate);
        if (ownedRE.size() == 0) {
            haveRealEstate = false;
        }
    }


    /**
     * Metóda slúži na kontrolu telefónneho čísla, ktoré má byť uložené do objektu. Kontroluje len čísla slovenského
     * formátu.
     * @param phoneNumber telefónne číslo, ktoré chceme uložiť
     * @throws PhoneNumberFormatException ak {@code phoneNumber} nespĺňa nejaké formátové kritéria, hodí vlastnú
     * výnimku
     */
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


    /**Metóda slúži na kontrolu mailovej adresy, ktorá má byť uložené do objektu.
     * @param usersEmail mail, ktorý chceme uložiť
     * @throws EmailFormatException ak {@code usesrsEmail} nespĺňa nejaké formátové kritéria, hodí vlastnú výnimku
     */
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

}
