package ViewContollers;

import CadasterObjects.CadasterObject;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import Offices.GeodesyOffice;
import OtherFunctionality.PopUpAlert;
import OtherFunctionality.RequestObserver;
import OtherFunctionality.SerializableUtility;
import Requests.Request;
import UserObject.Database;
import UserObject.User;

import java.util.ArrayList;
import java.util.List;

public class RequestsController {
    private Database usersDatabse;
    private RequestObserver requestObserver = new RequestObserver();

    private User office;
    private List<Request> requestList;

    public RequestsController(User user) {
        usersDatabse = new Database();
        office = user;
        requestList = office.getRequests();
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
            User requestingUser = request.getRequestingUser();
            User otherUser = request.getOtherUser();

            if (request.getCadasterObject().getRegisterNum() > 9999) { //real estate
                for (RealEstate realEstate : requestingUser.getOwner().getOwnedRE()) {
                    if (realEstate.getRegisterNum() == request.getCadasterObject().getRegisterNum()) {

                        otherUser.getOwner().addRE(realEstate);
                        requestingUser.getOwner().deleteRE(realEstate);

                        request.setAccepted(true);

                        requestingUser.replaceRequest(request);
                        otherUser.replaceRequest(request);

                        usersDatabse.getUsersDataHM().replace(requestingUser.getUsername(), requestingUser);
                        usersDatabse.getUsersDataHM().replace(otherUser.getUsername(), otherUser);

                        break;
                    }
                }
            } else { //land
                for (Land land : requestingUser.getOwner().getOwnedLands()) {
                    if (land.getRegisterNum() == request.getCadasterObject().getRegisterNum()) {

                        otherUser.getOwner().addLand(land);
                        requestingUser.getOwner().deleteLand(land);

                        request.setAccepted(true);

                        requestingUser.replaceRequest(request);
                        otherUser.replaceRequest(request);

                        usersDatabse.getUsersDataHM().replace(requestingUser.getUsername(), requestingUser);
                        usersDatabse.getUsersDataHM().replace(otherUser.getUsername(), otherUser);

                        break;
                    }
                }
            }

            SerializableUtility.saveUsers(usersDatabse.getUsersDataHM());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void rejectButtonClicked(String requestNumber) {
        Request request = getRequest(requestNumber);
        request.setRejected(true);

        User requestingUser = request.getRequestingUser();
        User otherUser = request.getOtherUser();

        requestingUser.replaceRequest(request);
        otherUser.replaceRequest(request);

        usersDatabse.getUsersDataHM().replace(requestingUser.getUsername(), requestingUser);
        usersDatabse.getUsersDataHM().replace(otherUser.getUsername(), otherUser);

        SerializableUtility.saveUsers(usersDatabse.getUsersDataHM());

        System.out.println("Reject"+requestNumber);
    }

    private Request getRequest(String string) {
        for (Request r : requestList) {
            if (r.getNumber().equalsIgnoreCase(string)) {
                return  r;
            }
        }
        return null;
    }

}
