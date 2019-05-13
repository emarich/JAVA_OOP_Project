package MyExceptions;


/**
 * Výnimka definovaná pri zlom vložení hodnoty pre {@code enum Landform}
 */
public class WrongLandformInputException extends Exception {
    public WrongLandformInputException(String message) { super(message); }
}
