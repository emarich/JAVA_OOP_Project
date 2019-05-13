package OtherFunctionality;


import javafx.scene.control.Alert;

/**
 * Daná trieda sa často používa spolu s vlastnými výnimkami. Výnimky sa vždy hádžu aj so správou, ktorá upozorňuje na
 * konkrétny problém v určitej časti programu a ak sa chytí vlastná výnimka, tak daná správa sa zároveň vypíše aj v danom
 * Alerte a tak vieme pomocou GUI upozorniť používateľa na určitú chybu
 */
public class PopUpAlert extends Alert {
    public PopUpAlert(AlertType alertType, String message) {
        super(alertType);
        this.setContentText(message);
        this.showAndWait();
    }
}
