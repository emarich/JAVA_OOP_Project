package OtherFunctionality;

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
        for (String s : usersDatabase.getUsersDataHM().keySet()) {
            textArea.appendText(s+"\n");
        }

    }

    public void start() {
        System.out.println("Starting "+ tName);
        if (printThread == null) {
            printThread = new Thread(this);
            printThread.start();
        }
    }
}
