package MyExceptions;


/**
 * Výnimka definovaná pre upozornenie uživateľa, že jeho zadaná hodnota registračného čísla je už zapísnaá v danom meste
 */
public class SameRegNumException extends Exception {
    public SameRegNumException(String message) { super(message); }
}
