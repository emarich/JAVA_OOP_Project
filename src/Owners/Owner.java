package Owners;

import CadasterObjects.Address;
import UserObject.UserType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Owner extends Ownership implements Serializable {

    private String name;
    private String birthDate;
    private String mutualAddress;
    private String ownerID;
    private Gender gender;
    //private double money = 100000;

    public Owner(String name, String gender, String date, String address){
        this.name = name;
        setGender(gender);
        this.birthDate = date;
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

    public void setGender(String gender) {
        this.gender = Gender.fromString(gender);
    }
    public Gender getGender() {
        return gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getBirthDate() {
        return birthDate;
    }

    public String getMutualAddress() {
        return mutualAddress;
    }
    public void setMutualAddress(String address) {
        this.mutualAddress = Address.setPerfectAddress(address);
    }


    //Getters and Setters-------------------------------------------------------

    public void generateID() {
        String[] IDN = new String[10];


    }


    //checks, if the date of birth is in the valid format dd.MM.yyyy
    public static void isValidDateFormat(String date) throws DateTimeParseException {
        String format = "dd.MM.yyyy";
        Locale locale = Locale.GERMAN;

        LocalDate ldt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Date has a wrong format.\n" +
                    "Right format: dd.MM.yyyy");
            throw e;
        }

    }

}
