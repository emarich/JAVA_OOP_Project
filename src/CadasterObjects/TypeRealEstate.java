package CadasterObjects;

public enum TypeRealEstate {
    RESIDENTAL, COMERCIAL, INDUSTRIAL, FARM;

    public static TypeRealEstate fromString(String string) {
        if (string.equals("RESIDENTAL")) {
            return RESIDENTAL;
        } else if (string.equals("COMERCIAL")) {
            return COMERCIAL;
        } else if (string.equals("INDUSTRIAL")) {
            return INDUSTRIAL;
        }
        return FARM;
    }

    public static String toString(TypeRealEstate typeRealEstate) {
        if (typeRealEstate == RESIDENTAL) {
            return "RESIDENTAL";
        } else if (typeRealEstate == COMERCIAL) {
            return "COMERCIAL";
        } else if (typeRealEstate == INDUSTRIAL) {
            return "INDUSTRIAL";
        }
        return "FARM";
    }
}
