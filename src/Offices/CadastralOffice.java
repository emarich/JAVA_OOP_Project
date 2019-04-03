package Offices;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import Owners.*;
import UserObject.Database;
import UserObject.User;

public class CadastralOffice extends Office {
    private Database usersData = new Database();
    private User user = new User();
    //private HashMap<String, User> usersHMap = new HashMap<>();


    public void registerNewUser(String username, String password, String userType) {
        usersData.addUser(username, password, userType =);
    }

    public void makeOwnerFromUser (String username, String name, String birthdate, String address) {
        usersData.getUser(username).setOwner(new Owner(name, birthdate, address));
    }

    //potrebene upravy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void makeLand (String username, int regNum, String city, int area, String typeLand) {
        usersData.getUser(username).getOwner().addLand(new Land(regNum, city, area, typeLand));
        usersData.getUser(username).getOwner().setHaveLand(true);
        if (usersData.getUser(username).getOwner().getHaveRealEstate()) {
            for (RealEstate RE : usersData.getUser(username).getOwner().getOwnedRE()) {
                for (Land l : usersData.getUser(username).getOwner().getOwnedLands()) {
                    if (RE.getRegisterNum() == l.getRegisterNum() &&
                            RE.getCity().equals(l.getCity())) {
                        usersData.getUser(username).getOwner().
                }
            }
        }

    }

    public void ownerGotLand () {

    }

    /*public void makeLandOwnerFromUser (String username, String name, String birthdate, String address) {
        User landOwnerUser = getUser(username);
        landOwnerUser.setLandOwner(new (name, birthdate, address));
    }

    public void makeREOwnerFromUser (String username, String name, String birthdate, String address) {
        User REOwnerUser = getUser(username);
        REOwnerUser.setReOwner(new REOwner(name, birthdate, address));
    }*/


}