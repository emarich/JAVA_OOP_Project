package ViewContollers;

import CadasterObjects.Land;
import Offices.GeodesyOffice;
import OtherFunctionality.PopUpAlert;
import OtherFunctionality.RequestObserver;
import Requests.Request;
import UserObject.User;

import java.util.ArrayList;
import java.util.List;

public class RequestsController {
    private RequestObserver requestObserver = new RequestObserver();

    private User office;
    private List<Request> requestList;

    public RequestsController(User user) {
        office = user;
        requestList = office.getRequests();
    }

    public void geodesyButtonClicked(String requestNumber) {
        try {
            GeodesyOffice geodesyOffice = new GeodesyOffice();
            Request request = getRequest(requestNumber);

            User user = request.getRequestingUser();
            Land land;

            for (Land l : user.getOwner().getOwnedLands()) {
                if (l.toString().equalsIgnoreCase(request.getCadasterObject().toString())) {
                    land = l;
                    break;
                }
            }



        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        System.out.println("Geodesy"+requestNumber);
    }

    public void acceptButtonClicked(String requestNumber) {
        System.out.println("Accept"+requestNumber);
    }

    public void rejectButtonClicked(String requestNumber) {
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
