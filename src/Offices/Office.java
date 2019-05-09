package Offices;

import Owners.Owner;
import Requests.Request;
import UserObject.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Office implements Serializable {
    private List<Request> citizensRequests = new ArrayList<>();
    private List<Request> officeRequests = new ArrayList<>();

    private long deposids;

    public void setCitizensRequests(List<Request> citizensRequests) {
        this.citizensRequests = citizensRequests;
    }
    public List<Request> getCitizensRequests() {
        return citizensRequests;
    }

    public void setOfficeRequests(List<Request> officeRequests) {
        this.officeRequests = officeRequests;
    }
    public List<Request> getOfficeRequests() {
        return officeRequests;
    }

    public void setDeposids(long deposids) {
        this.deposids = deposids;
    }
    public long getDeposids() {
        return deposids;
    }

    public void acceptRequest(Office office, Request request, int deposid) {
        officeRequests.add(request);
        this.deposids += deposid;
    }

    public void acceptRequest(Owner owner, Request request, int deposid) {
        citizensRequests.add(request);
        this.deposids += deposid;
    }
}
