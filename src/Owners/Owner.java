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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Owner extends Ownership implements Serializable {


    private String name;
    private String birthDate;
    private String mutualAddress;
    //private double money = 100000;

    public Owner(String name, String date, String address){
        this.name = name;
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

    //checks, if the date of birth is in the valid format dd.MM.yyyy
    public static boolean isValidDateFormat(String date) {
        String format = "dd.MM.yyyy";
        Locale locale = Locale.GERMAN;

        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(date, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(date);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(date, fomatter);
                String result = ld.format(fomatter);
                return result.equals(date);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(date, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(date);
                } catch (DateTimeParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

}
