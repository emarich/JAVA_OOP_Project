package MyExceptions;


/**
 * Výnimka definovaná pre zlý formát emailovej adresy
 */
public class EmailFormatException extends Exception {
    public EmailFormatException(String message) { super(message); }
}
