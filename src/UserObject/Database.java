package UserObject;

import OtherFunctionality.SerializableUtility;
import Owners.Owner;
import Owners.Ownership;
import Requests.Request;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Trieda {@code Database} ukladá všetkých používateľov do HaskMap {@link .usersData}, ktorí sa ukladjú podľa kľúča
 * , ktorý je vo forme {@code username} používateľa.
 * Daná HashMap {@link .usersData} sa serializuje v {@link SerializableUtility}.
 */
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
        SerializableUtility.saveUsers(usersData);
    }


    public List<Owner> findOwnerByName(String name) {
        List<Owner> owners = new ArrayList<>();
        for (String s : usersData.keySet()) {
            Ownership owner = getUser(s).getOwner();
            if (owner instanceof Owner) {
                if (((Owner) owner).getName().equalsIgnoreCase(name)) {
                    owners.add((Owner) owner);
                }
            }
        }
        return owners;
    }

    public Owner findOwnerByID(String id) {
        for (String s : usersData.keySet()) {
            Ownership owner = getUser(s).getOwner();
            if (owner instanceof Owner) {
                if (((Owner) owner).getOwnerID().equalsIgnoreCase(id)) {
                    return (Owner) owner;
                }
            }
        }
        return null;
    }

    public User findUserByOwner(Owner o) {
        for (String s : usersData.keySet()) {
            Ownership owner = getUser(s).getOwner();
            if (owner instanceof Owner) {
                if (((Owner) owner) == o) {
                    return getUser(s);
                }
            }
        }
        return null;
    }

    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        for (String u : usersData.keySet()) {
            if (getUser(u).getUserType().equals(UserType.OFFICE)) {
                if (getUser(u).getRequests() != null || !(getUser(u).getRequests().isEmpty())) {
                    requests.addAll(getUser(u).getRequests());
                }
            }
        }
        return requests;
    }

}
