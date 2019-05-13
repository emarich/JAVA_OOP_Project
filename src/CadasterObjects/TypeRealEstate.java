package CadasterObjects;

/**
 * Enumerický typ pre definovanie typu budovy
 */
public enum TypeRealEstate {
    RESIDENTIAL, COMMERCIAL, INDUSTRIAL, FARM;

    public static TypeRealEstate fromString(String string) {
        if (string.equals("RESIDENTIAL")) {
            return RESIDENTIAL;
        } else if (string.equals("COMMERCIAL")) {
            return COMMERCIAL;
        } else if (string.equals("INDUSTRIAL")) {
            return INDUSTRIAL;
        }
        return FARM;
    }

    public static String toString(TypeRealEstate typeRealEstate) {
        if (typeRealEstate == RESIDENTIAL) {
            return "RESIDENTIAL";
        } else if (typeRealEstate == COMMERCIAL) {
            return "COMMERCIAL";
        } else if (typeRealEstate == INDUSTRIAL) {
            return "INDUSTRIAL";
        }
        return "FARM";
    }
}
