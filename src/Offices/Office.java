package Offices;

import Owners.Owner;
import Requests.Request;
import UserObject.User;

import java.io.Serializable;

public abstract class Office implements Serializable {
    private Request request = new Request();

    public void acceptRequest(Office office) {

    }

    public void acceptRequest(Owner owner) {

    }
}
