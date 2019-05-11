package Requests;

import CadasterObjects.CadasterObject;
import UserObject.User;

import java.io.Serializable;
import java.util.Random;

public class Request implements Serializable {
    private String number;
    private User requestingUser;
    private User otherUser;
    private RequestType requestType;
    private boolean accepted = false;
    private CadasterObject cadasterObject;
    private String message;

    public Request(){}

    public Request(User user1, User user2, RequestType requestType, CadasterObject cadasterObject) {
        generateRequestNumber();
        this.requestingUser = user1;
        this.otherUser = user2;
        this.requestType = requestType;
        this.cadasterObject = cadasterObject;
    }

    public Request(User user1, RequestType requestType, CadasterObject cadasterObject) {
        generateRequestNumber();
        this.requestingUser = user1;
        this.requestType = requestType;
        this.cadasterObject= cadasterObject;
    }

    private void generateRequestNumber() {
        Random rand = new Random();
        this.number = String.format("%04d", rand.nextInt(10000));
    }

    public String getNumber() {
        return number;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public CadasterObject getCadasterObject() {
        return cadasterObject;
    }

    public String getMessage() {
        return message;
    }

    /*type of requests -
     * - zriadenie zalozneho prava - ak si ho bude chciet ofice zobrat, kludne moze len tak ho previest
     * - manzelska zmluva - owned property sa stane spolocne pre dvoch ownerov*/
    //message

    /*
    vydanie stav povolenia
    povolenie ostranenia stavby
    zmena supisneho cisla
    ziadost o povolenie terennych uprav
     */

}
