package ViewContollers;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import Offices.GeodesyOffice;
import OtherFunctionality.RequestObserver;
import OtherFunctionality.SerializableUtility;
import Requests.Request;
import UserObject.Database;
import UserObject.User;
import UserObject.UserType;

import java.util.ArrayList;
import java.util.List;

public class RequestsController {
    private Database usersDatabase = new Database();
    private RequestObserver requestObserver = new RequestObserver();

    private List<User> offices = new ArrayList<>();
    private List<Request> requestList;

    public RequestsController() {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());

        for (String u : usersDatabase.getUsersDataHM().keySet()) {
            if (usersDatabase.getUser(u).getUserType().equals(UserType.OFFICE)) {
                offices.add(usersDatabase.getUser(u));
            }
        }

        requestList = usersDatabase.getAllRequests();
    }

    public void geodesyButtonClicked(String requestNumber) {
        try {
            Request request = getRequest(requestNumber);
            boolean bool;
            User user = request.getRequestingUser();
            Land land = new Land();

            for (Land l : user.getOwner().getOwnedLands()) {
                if (l.toString().equalsIgnoreCase(request.getCadasterObject().toString())) {
                    land = l;
                    break;
                }
            }

            GeodesyOffice geodesyOffice = new GeodesyOffice(land);
            bool = geodesyOffice.considerRequest(request);

            if (bool) {
                request.setAccepted(true);
            } else {
                request.setRejected(true);
            }

            requestObserver.update(request);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void acceptButtonClicked(String requestNumber) {
        try {
            Request request = getRequest(requestNumber);

            request.setAccepted(true);

            User requestingUser = request.getRequestingUser();
            User otherUser = request.getOtherUser();

            if (request.getCadasterObject().getRegisterNum() > 9999) { //real estate
                for (RealEstate realEstate : requestingUser.getOwner().getOwnedRE()) {
                    if (realEstate.getRegisterNum() == request.getCadasterObject().getRegisterNum()) {

                        otherUser.getOwner().addRE(realEstate);
                        requestingUser.getOwner().deleteRE(realEstate);


                        for (User u : offices) {
                            u.replaceRequest(request);
                            usersDatabase.getUsersDataHM().replace( u.getUsername(),  u);
                        }

                        requestingUser.replaceRequest(request);
                        otherUser.replaceRequest(request);

                        usersDatabase.getUsersDataHM().replace(requestingUser.getUsername(), requestingUser);
                        usersDatabase.getUsersDataHM().replace(otherUser.getUsername(), otherUser);

                        break;
                    }
                }
            } else { //land
                for (Land land : requestingUser.getOwner().getOwnedLands()) {
                    if (land.getRegisterNum() == request.getCadasterObject().getRegisterNum()) {

                        otherUser.getOwner().addLand(land);
                        requestingUser.getOwner().deleteLand(land);

                        for (User u : offices) {
                            u.replaceRequest(request);
                            usersDatabase.getUsersDataHM().replace( u.getUsername(),  u);
                        }

                        requestingUser.replaceRequest(request);
                        otherUser.replaceRequest(request);

                        usersDatabase.getUsersDataHM().replace(requestingUser.getUsername(), requestingUser);
                        usersDatabase.getUsersDataHM().replace(otherUser.getUsername(), otherUser);

                        break;
                    }
                }
            }

            SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void rejectButtonClicked(String requestNumber) {
        Request request = getRequest(requestNumber);
        request.setRejected(true);

        User requestingUser = request.getRequestingUser();
        User otherUser = request.getOtherUser();

        for (User u : offices) {
            u.replaceRequest(request);
            usersDatabase.getUsersDataHM().replace( u.getUsername(),  u);
        }
        requestingUser.replaceRequest(request);
        otherUser.replaceRequest(request);

        usersDatabase.getUsersDataHM().replace(requestingUser.getUsername(), requestingUser);
        usersDatabase.getUsersDataHM().replace(otherUser.getUsername(), otherUser);

        SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

        System.out.println("Reject"+requestNumber);
    }

    private Request getRequest(String string) {
        for (Request r : requestList) {
            for (User u : offices) {
                if (r.getNumber().equalsIgnoreCase(string) || r.equals(u.getRequest(string))) {
                    return  r;
                }
            }
        }
        return null;
    }

}
