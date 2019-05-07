package MyExceptions;

public class PhoneNumberFormatException extends Exception {
    public PhoneNumberFormatException() { super(); }
    public PhoneNumberFormatException(String message) { super(message); }
    public PhoneNumberFormatException(String message, Throwable cause) { super(message, cause); }
    public PhoneNumberFormatException(Throwable cause) { super(cause); }
}
