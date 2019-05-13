package MyExceptions;


/**
 * Výnimka definovaná pre upozornenie uživateľa pri vložení hodnoty akéhokoľvek typu
 */
public class WrongInputException extends Exception {
    public WrongInputException(String message) { super(message); }
}
