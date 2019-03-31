package Owners;

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
