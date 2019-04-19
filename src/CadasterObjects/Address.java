package CadasterObjects;

import java.io.Serializable;

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


    public void setAddress(String address) {
        String[] outputAddress = new String[3];
        try {
            outputAddress = address.split(",");
            if (outputAddress[0] == null || outputAddress[1] == null || outputAddress[2] == null) {
                System.out.println("You must enter street, city and state");
                //PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING,
                //"You must enter street, city and state");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, write it in this format: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
            //PopUpAlert alert = new PopUpAlert(Alert.AlertType.INFORMATION,
            //"Please, write it in this format: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
        }
        setStreetAndNum(outputAddress[0]);
        setCity(outputAddress[1]);
        setState(outputAddress[2]);
        outputAddress = null;
    }
}
