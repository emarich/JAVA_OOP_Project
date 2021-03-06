package UserObject;

import java.io.Serializable;

public enum  UserType {
    CITIZEN, OFFICE;

    public static UserType fromString(String string) {
        if (string.equalsIgnoreCase("OFFICE")) {
            return OFFICE;
        }
        return CITIZEN;
    }

    public static String toString(UserType userType) {
        if (userType == OFFICE) {
            return "OFFICE";
        }
        return "CITIZEN";
    }
}
