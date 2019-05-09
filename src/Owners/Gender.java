package Owners;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String string) {
        if (string.equalsIgnoreCase("MALE")) {
            return MALE;
        }
        return FEMALE;
    }
}
