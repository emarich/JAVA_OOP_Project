package OtherFunctionality;

import UserObject.Database;
import javafx.scene.control.TextArea;


/**
 * Observer pre aktualizáciu údajov v {@code TextArea} na hlavnom okne používateľa.
 * Akualizuje sa pri pridaní nového používateľa, nových údajov priradeným používateľovi, evidovaní pozemku alebo
 * budovy
 */
public class DataObserver extends TextArea implements Observer {

    private Database usersDatabase = new Database();
    private PrintCadastre printCadastre;

    public void setUsersDatabase(Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public DataObserver() {}

    public DataObserver(Database database) {
        this.usersDatabase = database;
    }

    /**
     * Metóda {@code update(Object o)} je implementovaná z rozhrania {@code Observer}, ktoré implementuje daná tieda.
     */
    @Override
    public void update() {
        this.setText("");
        printCadastre = new PrintCadastre(usersDatabase, this);
        printCadastre.print();
        printCadastre = null;
    }

    /**
     * @param o inštancia {@link Database}
     */
    @Override
    public void update(Object o) {
        this.setText("");
        printCadastre = new PrintCadastre((Database) o, this);
        printCadastre.print();
        printCadastre = null;
    }
}