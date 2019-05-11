package OtherFunctionality;

import CadasterObjects.Land;
import UserObject.Database;
import javafx.scene.control.TextArea;


public class DataObserver extends TextArea implements Observer {
    private Database usersDatabase = new Database();
    //private PrintCadastreThread printCadastreThread;
    private PrintCadastre printCadastre;

    public void setUsersDatabase(Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public DataObserver() {}

    public DataObserver(Database database) {
        this.usersDatabase = database;
    }


    public void update() {

        /*for (Land l : usersDatabase.getUser("Office").getOwner().getOwnedLands()) {
            if (l == null) {
                break;
            }
            System.out.println(l.getRegisterNum()+"\n");
        }*/
        this.setText("");
        //printCadastreThread = new PrintCadastreThread(usersDatabase, "PrintThread", this);
        //printCadastreThread.start();
        printCadastre = new PrintCadastre(usersDatabase, this);
        printCadastre.print();
    }
}