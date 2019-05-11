package Requests;

public enum  RequestType {
    SALE("Sale property"),
    GIVE("Give property"),
    EXCHANGE("Exchange properties"),
    MARRIAGE("Marriage contract"),
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
}
