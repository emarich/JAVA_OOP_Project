package Requests;

import MyExceptions.WrongInputException;

import java.io.Serializable;

public enum  RequestType implements Serializable {
    SALE("Sale property"),
    GIVE("Give property"),
    BUILD("Build permission"),
    DEMOLITION("Demolition permission"),
    TERRAIN("Terrain treatment");

    private final String description;

    public String getDescription() {
        return description;
    }

    private RequestType(String shortName) {
        this.description = shortName;
    }

    public static RequestType getRequestFromAttribute(String m) throws WrongInputException {
        switch (m) {
            case "Sale property": return SALE;
            case "Give property": return GIVE;
            case "Build permission": return BUILD;
            case "Demolition permission": return DEMOLITION;
            case "Terrain treatment": return TERRAIN;
        }
        throw new WrongInputException("Request cannot found by attribute "+m);
    }

}
