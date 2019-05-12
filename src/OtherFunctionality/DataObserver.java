package OtherFunctionality;

import CadasterObjects.Land;
import UserObject.Database;
import javafx.scene.control.TextArea;


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

    @Override
    public void update() {
        this.setText("");
        printCadastre = new PrintCadastre(usersDatabase, this);
        printCadastre.print();
        printCadastre = null;
    }

    @Override
    public void update(Object o) {
        this.setText("");
        printCadastre = new PrintCadastre((Database) o, this);
        printCadastre.print();
        printCadastre = null;
    }
}