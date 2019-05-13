package ViewContollers;

import CadasterObjects.*;
import MyExceptions.WrongInputException;
import OtherFunctionality.*;
import Owners.Owner;
import Requests.Request;
import Requests.RequestType;
import UserObject.Database;
import UserObject.User;
import UserObject.UserType;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Vytvára nové requesty, ktoré vie vytvárať obyčajný používateľ (vlastník). Odosiela ich administrátorm.
 */
public class NewRequestController {
    private Database usersDatabase;
    private User user;
    private List<CadasterObject> cadasterObjectList = new ArrayList<>();


    public NewRequestController(User user, Database usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.user = user;
    }


    public ObservableList<String> setPropertyList(Owner owner, ObservableList<String> propertyList) {
        if (owner != null) {
            if (!(owner.getOwnedLands().isEmpty()) || owner.getOwnedLands()!=null){
                for (Land l : owner.getOwnedLands()) {
                    propertyList.add(l.toString());
                }
            }
            if (!(owner.getOwnedRE().isEmpty()) || owner.getOwnedRE()!=null){
                for (RealEstate l : owner.getOwnedRE()) {
                    propertyList.add(l.toString());
                }
            }

        }
        return propertyList;
    }

    public ObservableList<String> setPropertyList(Owner owner, String obj, ObservableList<String> propertyList) {
        if (owner != null) {
            if (obj.equalsIgnoreCase("land")){
                if (!(owner.getOwnedLands().isEmpty()) || owner.getOwnedLands()!=null){
                    for (Land l : owner.getOwnedLands()) {
                        propertyList.add(l.toString());
                    }
                }
            } else if (obj.equalsIgnoreCase("realEstate")) {
                if (!(owner.getOwnedRE().isEmpty()) || owner.getOwnedRE()!=null){
                    for (RealEstate l : owner.getOwnedRE()) {
                        propertyList.add(l.toString());
                    }
                }
            }

        }
        return propertyList;
    }

    private void arrayFromObservableList(ObservableList<String> obList) {

        for (String s : obList) {
            if (user.getOwner().getHaveLand()) {
                for (Land l : user.getOwner().getOwnedLands()) {
                    if (l.toString().equalsIgnoreCase(s)) {
                        cadasterObjectList.add(l);
                    }
                }
            }

            if (user.getOwner().getHaveRealEstate()) {
                for (RealEstate re : user.getOwner().getOwnedRE()) {
                    if (re.toString().equalsIgnoreCase(s)) {
                        cadasterObjectList.add(re);
                    }
                }
            }
        }
    }

    public void sendRequest(User office, User secondUser, RequestType requestType, ObservableList<String> obList, Stage stage, String actualDate) throws WrongInputException {
        try {
            if (office.getUserType() != (UserType.OFFICE)) {
                throw new WrongInputException("Admin user not found");
            }

            arrayFromObservableList(obList);

            for (int i = 0; i < cadasterObjectList.size(); i++) {
                Request request = new Request(user, secondUser, requestType, cadasterObjectList.get(i), actualDate);
                System.out.println(request.getNumber());
                usersDatabase.getUser(user.getUsername()).addRequest(request);
                usersDatabase.getUser(secondUser.getUsername()).addRequest(request);
                usersDatabase.getUser(office.getUsername()).addRequest(request);

            }

            SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());
            stage.close();

        } catch (NullPointerException e) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, office.getUsername()+" doesn't have owner");
        }


    }

    public void sendRequest(User office, RequestType requestType, ObservableList<String> obList, Stage stage, String actualDate) throws WrongInputException {
        try {
            if (office.getUserType() != (UserType.OFFICE)) {
                throw new WrongInputException("Admin user not found");
            }

            arrayFromObservableList(obList);

            for (int i = 0; i < cadasterObjectList.size(); i++) {
                Request request = new Request(user, requestType, cadasterObjectList.get(i), actualDate);
                System.out.println(request.getNumber());
                usersDatabase.getUser(user.getUsername()).addRequest(request);
                usersDatabase.getUser(office.getUsername()).addRequest(request);
            }

            SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());
            stage.close();

        } catch (NullPointerException e) {
            PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, office.getUsername()+" doesn't have owner");
        }

    }
}
