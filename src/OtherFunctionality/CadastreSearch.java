package OtherFunctionality;

import CadasterObjects.CadasterObject;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import MyExceptions.SameRegNumException;
import Owners.Ownership;
import UserObject.Database;
import UserObject.User;
import java.io.Serializable;

/**
 * Trieda slúži na porovnávanie registračných čísel a novo vytvorenej nehnuteľnosti a ostatných nehnuteľností.
 * Ak sa nájde nechcený prípad, hodí výnimku.
 */
public class CadastreSearch implements Serializable {

    /**
     * Daná metóda porovnáva reqistrašé čísla
     * @param object s
     * @param database databáza užívateľov
     * @throws SameRegNumException výnimka, ktorá sa hodí, ak nájde 2 pozemky s rovnakým registračných číslom alebo už nemôže viac ukladať
     * budovy do pozemku
     */
    public static void compareRegNum(CadasterObject object, Database database)  throws SameRegNumException{
        int seekingNum = object.getRegisterNum(); //actual reg number of object

        User user;
        Ownership owner;

        for (String u : database.getUsersDataHM().keySet()) {
            user = database.getUser(u); //get user

            if (user.getIsOwner()) {
                owner = user.getOwner();

                if (owner.getHaveLand()) {
                    for (Land l : owner.getOwnedLands()) { //get users lands
                        if (l.getRegisterNum() == seekingNum) {
                            compareAddress(object, l, seekingNum); //throws error
                        }
                    }
                }

                if (object instanceof Land) {
                    if (owner.getHaveRealEstate()) {
                        for (RealEstate re : owner.getOwnedRE()) { //get users lands
                            if (re.getRegisterNum() > 9999) {
                                int currRegNum = re.getRegisterNum()/10;
                                if (currRegNum == seekingNum) {
                                    compareAddress(object, re, seekingNum); //throws error
                                }
                            } else if (re.getRegisterNum() == seekingNum) {
                                compareAddress(object, re, seekingNum); //throws error
                            }
                        }
                    }

                }

                if (object instanceof RealEstate) {
                    if (object.getRegisterNum() < 9999) {
                        object.setRegisterNum(object.getRegisterNum()*10);
                    }
                }
            }

        }
    }

    /**
     *
     * @param newObject novovytvorená nehnuteľnosť
     * @param current objekt, ktorý má rovnaké registrační číslo, ako {@code newObject}
     * @param regNum rovnaké registračné číslo
     * @throws SameRegNumException výnimka, ktorá sa hodí, ak nájde 2 pozemky s rovnakým registračných číslom alebo už nemôže viac ukladať
     * budovy do pozemku
     */
    private static void compareAddress(CadasterObject newObject, CadasterObject current, int regNum) throws SameRegNumException {
        String[] outputAddress;
        //newObject
        outputAddress = newObject.getAddress().split(",");// get whole address
        String newStreet = outputAddress[0].trim(); // get street
        String newTown = outputAddress[1].trim(); // get town
        //current
        outputAddress = current.getAddress().split(","); // get whole address
        String currentStreet = outputAddress[0].trim(); // get street
        String currentTown = outputAddress[1].trim(); // get town

        if (newObject instanceof Land && current instanceof RealEstate) {
            if (FindSubstring.findExact(newStreet, currentStreet) &&
                    FindSubstring.findExact(newTown, currentTown)) {
                ((Land) newObject).addRealEstate((RealEstate) current);
                ((RealEstate) current).addLand((Land) newObject);
                System.out.println("LAND has new real estate.");
            }
        } else if (newObject instanceof RealEstate && current instanceof Land) {
            if (FindSubstring.findExact(newStreet, currentStreet) &&
                    FindSubstring.findExact(newTown, currentTown)) {
                System.out.println(((Land)current).getHaveRE());
                if (((Land) current).getHaveRE()) {
                    int lastRegNum = ((Land) current).getLastRE().getRegisterNum();
                    System.out.println(lastRegNum % 10);
                    if (lastRegNum % 10 == 9) {
                        throw new SameRegNumException("Land: " + regNum + " can't have more real estates");
                    } else {
                        newObject.setRegisterNum(lastRegNum+1);
                    }
                } else {
                    newObject.setRegisterNum(newObject.getRegisterNum()*10);
                }
                ((RealEstate) newObject).addLand((Land) current);
                ((Land) current).addRealEstate((RealEstate) newObject);
                System.out.println("REAL ESTATE has new land.");
            }
        }

        if (newObject instanceof Land && current instanceof Land) {
            if (FindSubstring.findExact(newTown, currentTown)) {
                throw new SameRegNumException("Land: "+regNum+" already exists in "+newTown);
            }
        }
    }


    //concrete search
}
