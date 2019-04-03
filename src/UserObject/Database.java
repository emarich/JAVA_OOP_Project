package UserObject;

import OtherFunctionality.PopUp;
import javafx.scene.control.Alert;
import sun.security.util.Password;

import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
    private HashMap<String, User> usersData = new HashMap<>();

    public void addUser(String username, String password, String userType) {
        if (existingUser(username)) {
            PopUp alert = new PopUp(Alert.AlertType.WARNING, "Username "+username+" is already used.");
        } else {
            usersData.put(username, new User(username, password, userType));
        }
    }

    public User getUser(String username) {
        return usersData.get(username);
    }

    public boolean existingUser(String username) {
        for (String u : usersData.keySet()) {
            if (u.equals(username))  {
                return true;
            }
        }
        return false;
    }

    public void printUsers(HashMap<String, User> users){
        for (String u : users.keySet()) {
            System.out.println(u);
        }
    }

    public HashMap<String, User> getUsersData() {
        return usersData;
    }

    public void changeUsername(String oldUsername, String newUsername ) {
        if (existingUser(newUsername)) {
            PopUp alert = new PopUp(Alert.AlertType.WARNING, "Username "+newUsername+" is already used.");
        } else {
            usersData.put(newUsername, usersData.get(oldUsername));
            usersData.remove(oldUsername);
        }
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        User tmp = usersData.get(username);
        if (tmp.getPassword().equals(oldPassword)) {
            tmp.setPassword(newPassword);
            usersData.put(username, tmp);
            tmp = null;
        } else {
            PopUp alert = new PopUp(Alert.AlertType.WARNING, "Wrong current password.");
        }
    }
}
