package Offices;

import Owners.Owner;
import UserObject.User;

public abstract class Office {
    private String phoneNum;
    private String email;

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void acceptRequest(Office office) {

    }

    public void acceptRequest(Owner owner) {

    }
}
