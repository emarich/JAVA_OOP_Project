package UserObject;

import OtherFunctionality.PopUpAlert;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.HashMap;

public class Database implements Serializable {
    private HashMap<String, User> usersData = new HashMap<>();

    //prida usera do hashMapy ^
    public void addUser(String username, String password, String userType) {
        if (existingUser(username)) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Username "+username+" is already used.");
        } else {
            usersData.put(username, new User(username, password, userType));
        }
    }

    //vyberie konkretneho usera z hashMapy podla username
    public User getUser(String username) {
        return usersData.get(username);
    }

    //vrati hashMapu
    public HashMap<String, User> getUsersDataHM() {
        return usersData;
    }

    //zisti, ci zadany user existuje
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

    //ak je hashmap plna, tak ju najprv



    public void changeUsername(String oldUsername, String newUsername ) {
        if (existingUser(newUsername)) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Username "+newUsername+" is already used.");
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
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Wrong current password.");
        }
    }



}
