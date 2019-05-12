package OtherFunctionality;

import Requests.Request;
import UserObject.Database;
import UserObject.User;
import UserObject.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestObserver implements Observer, Serializable {
    private Database usersDatabase = new Database();
    private boolean accepted = false;
    private boolean rejected = false;
    private List<Request> requestList = new ArrayList<>();
    //private String requestNumber;

    public RequestObserver(Request request) {
        this.requestList.add(request);
    }

    public RequestObserver() {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
        for ( String s : usersDatabase.getUsersDataHM().keySet()) {
            if (usersDatabase.getUser(s).getUserType().equals(UserType.OFFICE)) {
                if (usersDatabase.getUser(s).getRequests() != null ||
                        !(usersDatabase.getUser(s).getRequests().isEmpty())) {
                    requestList.addAll(usersDatabase.getUser(s).getRequests());
                }
            }
        }
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
    public void update(Object o) {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());

        if (requestList == null || requestList.isEmpty()) {
            for ( String s : usersDatabase.getUsersDataHM().keySet()) {
                if (usersDatabase.getUser(s).getUserType().equals(UserType.OFFICE)) {
                    if (usersDatabase.getUser(s).getRequests() != null ||
                            !(usersDatabase.getUser(s).getRequests().isEmpty())) {
                        requestList.addAll(usersDatabase.getUser(s).getRequests());
                    }
                }
            }
        }

        for (Request r : requestList) {

            if (r.getNumber().equalsIgnoreCase((String) o)) {

                if (r.getRequestingUser() != null) {
                    r.getRequestingUser().getRequest((String) o).setAccepted(accepted);
                    r.getRequestingUser().getRequest((String) o).setRejected(rejected);
                    usersDatabase.getUsersDataHM().replace( r.getRequestingUser().getUsername(),  r.getRequestingUser());
                }
                if (r.getOtherUser() != null){
                    r.getOtherUser().getRequest((String) o).setAccepted(accepted);
                    r.getOtherUser().getRequest((String) o).setRejected(rejected);
                    usersDatabase.getUsersDataHM().replace( r.getOtherUser().getUsername(),  r.getOtherUser());
                }

                SerializableUtility.saveUsers(usersDatabase.getUsersDataHM());

                requestList.remove(r);
                break;
            }
        }
    }

    @Override
    public void update() {

    }
}
