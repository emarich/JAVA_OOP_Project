package Owners;

import CadasterObjects.Address;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

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
        generateID();
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

    public String getOwnerID() {
        return ownerID;
    }
    //Getters and Setters-------------------------------------------------------

    private void generateID() {
        String[] idn = new String[11];

        idn[0] = String.valueOf(birthDate.charAt(8));
        idn[1] = String.valueOf(birthDate.charAt(9));
        String tmp =  String.valueOf(birthDate.charAt(3));
        if (gender.toString().equals("FEMALE")) {
            int foo = Integer.parseInt(tmp) + 5;
            tmp = String.valueOf(foo);
            idn[2] = tmp;
        } else {
            idn[2] = tmp;
        }
        idn[3] = String.valueOf(birthDate.charAt(4));
        idn[4] = String.valueOf(birthDate.charAt(0));
        idn[5] = String.valueOf(birthDate.charAt(1));
        idn[6] = "/";

        Random rand = new Random();
        for (int i = 7; i < 11; i++) {
            idn[i] = String.format("%d", rand.nextInt(10));
        }

        ownerID = String.join("", idn);
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
