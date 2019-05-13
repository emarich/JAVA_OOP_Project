package MyExceptions;


/**
 * Výnimka definovaná pre zlý formát telefónneho čísla
 */
public class PhoneNumberFormatException extends Exception {
    public PhoneNumberFormatException(String message) { super(message); }
}
