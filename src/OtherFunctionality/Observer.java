package OtherFunctionality;

/**
 * Rozhranie {@code Observer} sa implementuje v {@link DataObserver}
 */
public interface Observer {
    void update();
    void update(Object o);
}
