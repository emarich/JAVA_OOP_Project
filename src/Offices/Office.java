package Offices;

import Requests.Request;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated prvotná myšlienka využívala danú triedu, no v konečnej verzii je jej funkcionalita použitá len na konkrétnych
 * miestach a iným spôsobom, momentálne je len využitá len na dediace úcely
 */
public class Office implements Serializable {
    private List<Request> requests = new ArrayList<>();

    public void acceptRequest(Request request) {
        requests.add(request);
    }

}
