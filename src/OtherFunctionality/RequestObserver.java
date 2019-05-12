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

    public RequestObserver() {
        usersDatabase.setUsersDataHM(SerializableUtility.loadUsers());
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
                    r.getRequestingUser().getRequest(((Request)o).getNumber()).setAccepted(accepted);
                    r.getRequestingUser().getRequest(((Request)o).getNumber()).setRejected(rejected);
                    usersDatabase.getUsersDataHM().replace( r.getRequestingUser().getUsername(),  r.getRequestingUser());
                }
                if (r.getOtherUser() != null){
                    r.getOtherUser().getRequest(((Request)o).getNumber()).setAccepted(accepted);
                    r.getOtherUser().getRequest(((Request)o).getNumber()).setRejected(rejected);
                    usersDatabase.getUsersDataHM().replace( r.getOtherUser().getUsername(),  r.getOtherUser());
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
