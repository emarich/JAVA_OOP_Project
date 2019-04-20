package OtherFunctionality;

import Owners.City;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.TextArea;


public class PrintCadastreThread implements Runnable {
    private Thread printThread;
    private Database usersDatabase;
    private String tName;
    private TextArea textArea;

    public PrintCadastreThread (Database database, String name, TextArea textArea1) {
        usersDatabase = database;
        tName = name;
        textArea = textArea1;
        System.out.println("Creating "+ tName);
    }

    public void run() {
        System.out.println("Running "+ tName);
        Ownership owner;
        for (String s : usersDatabase.getUsersDataHM().keySet()) {
            owner = usersDatabase.getUser(s).getOwner();

            textArea.appendText("\n|| Username: "+s+" ||\n");
            if (owner instanceof Owner) {
                textArea.appendText("|| Name: "+((Owner) owner).getName()+"\n");
                textArea.appendText("|| Mutual address: "+((Owner) owner).getMutualAddress()+"\n");
            } else if (owner instanceof City){
                textArea.appendText("|| Phone number: "+((City) owner).getPhoneNumber()+"\n");
                textArea.appendText("|| Email: "+((City) owner).getEmail()+"\n");
            } else {
                textArea.appendText("Nejaka chyba nastala.");
            }
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
            textArea.appendText("");
        }

    }

    public void start() {
        System.out.println("Starting "+ tName);
        if (printThread == null) {
            printThread = new Thread(this, tName);
            printThread.start();
        }
    }
}
