package Requests;

import CadasterObjects.CadasterObject;
import OtherFunctionality.RequestObserver;
import UserObject.User;
import java.io.Serializable;
import java.util.Random;

/**
 * Trieda je používaná vo funkcionalite požiadavok uživateľov.
 * Trieda agreguje {@code number} - 4 miestne číslo (ID) požiadavky, {@code requestingUser} - žiadateľa, {@code otherUser}
 * - prijímateľa, {@code requestType} - typ požiadavky, {@code cadasterObject} - objekt (nehnuteľnosť) požiadavky,
 * {@code date} - dátum vytvorenia požiadavky
 */

public class Request implements Serializable {
    private String number;
    private User requestingUser;
    private User otherUser;
    private RequestType requestType;
    private boolean accepted = false;
    private boolean rejected = false;
    private CadasterObject cadasterObject;
    private String date;

    public Request(){}

    public Request(User user1, User user2, RequestType requestType, CadasterObject cadasterObject, String date) {
        generateRequestNumber();
        this.requestingUser = user1;
        this.otherUser = user2;
        this.requestType = requestType;
        this.cadasterObject = cadasterObject;
        this.date = date;
    }

    public Request(User user1, RequestType requestType, CadasterObject cadasterObject, String date) {
        generateRequestNumber();
        this.requestingUser = user1;
        this.requestType = requestType;
        this.cadasterObject= cadasterObject;
        this.date = date;
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
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean getRejected() {
        return rejected;
    }
    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public CadasterObject getCadasterObject() {
        return cadasterObject;
    }

    public String getDate() {
        return date;
    }


    private void generateRequestNumber() {
        Random rand = new Random();
        this.number = String.format("%04d", rand.nextInt(10000));
    }
}
