package Offices;

import Requests.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GeodesyOffice extends Office implements Serializable {
    private List<Request> requests = new ArrayList<>();

    @Override
    public void acceptRequest(Request request) {
        requests.add(request);
    }
}



