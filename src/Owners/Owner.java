package Owners;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Owner {
    private String name;
    private Date birthDate;
    DateFormat dateFormat = new SimpleDateFormat("dd.mm.yy");
    //Date date = DATE_FORMAT.parse("2013-12-4"); -> date.toString
    private Address mutualAddress;
    private String[] outputAddress = new String[3];


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
        try {
            outputAddress = address.split(",");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format must be: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
        }
        mutualAddress.setStreetAndNum(outputAddress[0]);
        mutualAddress.setCity(outputAddress[1]);
        mutualAddress.setState(outputAddress[2]);
    }
    public Address getMutualAddress() {
        return mutualAddress;
    }
}
