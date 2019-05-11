package Offices;

import Owners.Owner;
import Requests.Request;
import UserObject.User;
import UserObject.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Office implements Serializable {
    private List<Request> requests = new ArrayList<>();


    public void acceptRequest(Request request) {
        requests.add(request);
    }

}
