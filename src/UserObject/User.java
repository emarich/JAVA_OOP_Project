package UserObject;

import Offices.CadastralOffice;
import Offices.Office;
import Owners.Owner;
import Owners.Ownership;
import Requests.Request;
import javafx.fxml.FXML;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private UserType userType;

    private Ownership owner = null;
    private boolean isOwner = false;

    private List<Request> requests = new ArrayList<>();

    public User(String username, String password, String userType) {
        setUsername(username);
        setPassword(password);
        setUserType(userType);
    }

    public User() {}

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

    public void setOwner(Ownership owner) {
        this.owner = owner;
    }
    public Ownership getOwner() {
        return owner;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }
    public boolean getIsOwner() {
        return isOwner;
    }

    //Getters and Setters-------------------------------------------------------


}
