package UserObject;

import OtherFunctionality.SerializableUtility;
import javafx.scene.control.Alert;
import java.io.*;
import java.util.HashMap;

public class Database implements Serializable {
    //users are saving to this
    private HashMap<String, User> usersData = new HashMap<>();

    //Getter and Setter
    public HashMap<String, User> getUsersDataHM() {
        return usersData;
    }
    public void setUsersDataHM(HashMap<String, User> hashMap) {
        usersData = hashMap;
    }
    //Getter and Setter

    //get concrete user from hashMap by username
    public User getUser(String username) {
        return usersData.get(username);
    }

    //find if user already exists
    public boolean existingUser(String username) {
        for (String u : usersData.keySet()) {
            if (u.equals(username))  {
                return true;
            }
        }
        return false;
    }

    //add user to hash map
    public void addUser(String username, String password, String userType) {
        User user = new User(username, password, userType);
        usersData.put(username, user);
        SerializableUtility.saveUsers(usersData);
        user = null;
    }

    public void printUsers() {
        for (String u : usersData.keySet()) {
            System.out.println(u+" "+usersData.get(u).getUserType().toString());
        }
    }

    public void deleteAllUsers() {
        for (String u : usersData.keySet()) {
            usersData.remove(u);
        }
    }

    public void changeUsername(String oldUsername, String newUsername ) {
        if (existingUser(newUsername)) {
            System.out.println("Username "+newUsername+" is already used.");
            //PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Username "+newUsername+" is already used.");
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
            System.out.println("Wrong current password.");
            //PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Wrong current password.");
        }
    }



}
