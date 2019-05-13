package OtherFunctionality;

import Requests.Request;
import UserObject.Database;
import UserObject.User;
import UserObject.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Aktualizuje stavy údajov pre requesty a upovedomuje o ich zmenách používateľov.
 * Implementované iba v {@link ViewContollers.RequestsController#geodesyButtonClicked(String) geodesyButtonClicked}
 */
public class RequestObserver implements Observer, Serializable {
    private Database usersDatabase = new Database();
    private boolean accepted = false;
    private boolean rejected = false;
    private List<Request> requestList;
    private List<User> offices = new ArrayList<>();
    //private String requestNumber;

    public RequestObserver() {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        for (String u : usersDatabase.getUsersDataHM().keySet()) {
            if (usersDatabase.getUser(u).getUserType().equals(UserType.OFFICE)) {
                offices.add(usersDatabase.getUser(u));
            }
        }
        requestList = usersDatabase.getAllRequests();
    }

    private void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    public boolean isAccepted() {
        return accepted;
    }
    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }
    public boolean isRejected() {
        return rejected;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    //definitive update
    @Override
    public void update(Object o) { //o = request number

        if (requestList == null || requestList.isEmpty()) {
            System.out.println("THERE ARE NO REQUESTS");
        }

        for (Request r : requestList) {

            if (r.getNumber().equalsIgnoreCase(((Request)o).getNumber())) {

                if (r.getRequestingUser() != null) {
                    r.getRequestingUser().replaceRequest((Request)o);
                    usersDatabase.getUsersDataHM().replace( r.getRequestingUser().getUsername(),  r.getRequestingUser());
                }
                if (r.getOtherUser() != null){
                    r.getOtherUser().replaceRequest((Request) o);
                    usersDatabase.getUsersDataHM().replace( r.getOtherUser().getUsername(),  r.getOtherUser());
                }

                for (User u : offices) {
                    u.replaceRequest((Request) o);
                    usersDatabase.getUsersDataHM().replace( u.getUsername(),  u);
                }

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                break;
            }
        }
    }

    @Override
    public void update() {

    }
}
