package MyExceptions;

public class WrongLandformInputException extends Exception {
    public WrongLandformInputException() { super(); }
    public WrongLandformInputException(String message) { super(message); }
    public WrongLandformInputException(String message, Throwable cause) { super(message, cause); }
    public WrongLandformInputException(Throwable cause) { super(cause); }
}
