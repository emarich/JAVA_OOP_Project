package UserObject;

import Owners.Owner;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private Owner owner;
    //private LandOwner landOwner = new LandOwner();
    //private REOwner reOwner = new REOwner();
    private UserType userType;

    public User(String username, String password, String userType) {
        setUsername(username);
        setPassword(password);
        setUserType(userType);
    }

    public User() {

    }

    //Getters and Setters-------------------------------------------------------

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setUserType(String userType) {
        this.userType = UserType.fromString(userType);
    }
    public UserType getUserType() {
        return userType;
    }

    /*public void setLandOwner(LandOwner landOwner) {
        this.landOwner = landOwner;
    }
    public LandOwner getLandOwner() {
        return landOwner;
    }

    public void setReOwner(REOwner reOwner) {
        this.reOwner = reOwner;
    }
    public REOwner getReOwner() {
        return reOwner;
    }*/

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public Owner getOwner() {
        return owner;
    }

    //Getters and Setters-------------------------------------------------------

}
