package MyExceptions;


/**
 * Výnimka definovaná pre zlý formát adresy nehnuteľnosti alebo majiteľa
 */
public class AddressFormatException extends Exception {
    public AddressFormatException(String message) { super(message); }
}


