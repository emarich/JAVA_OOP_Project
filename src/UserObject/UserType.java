package UserObject;

public enum  UserType {
    CITIZEN, OFFICE;

    public UserType fromString(String string) {
        if (string.equals("OFFICE")) {
            return OFFICE;
        }
        return CITIZEN;
    }

    public String toString(UserType userType) {
        if (userType == OFFICE) {
            return "OFFICE";
        }
        return "CITIZEN";
    }


}
