package OtherFunctionality;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import Owners.City;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
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

            textArea.appendText("\n|| Username: "+s+" \n");

            if (usersDatabase.getUser(s).getIsOwner()) {
                if (owner instanceof Owner) {
                    textArea.appendText("|| Name: "+((Owner) owner).getName()+"\n");
                    textArea.appendText("|| Mutual address: "+((Owner) owner).getMutualAddress()+"\n");
                } else if (owner instanceof City){
                    textArea.appendText("|| Phone number: "+((City) owner).getPhoneNumber()+"\n");
                    textArea.appendText("|| Email: "+((City) owner).getEmail()+"\n");
                } else {
                    textArea.appendText("Nejaka chyba nastala.\n");
                }

                textArea.appendText("LANDS:---------------------\n");
                if (owner.getHaveLand()) {
                    Land land;
                    for (int i = 0; i < owner.getOwnedLands().size(); i++) {
                        land = owner.getOwnedLands().get(i);
                        printFormula(land);
                    }
                    land = null;
                } else {
                    textArea.appendText("...none...\n");
                }

                textArea.appendText("REAL ESTATES:--------------\n");
                if (owner.getHaveRealEstate()) {
                    RealEstate realEstate;
                    for (int i = 0; i < owner.getOwnedRE().size(); i++) {
                        realEstate = owner.getOwnedRE().get(i);
                        printFormula(realEstate);
                    }
                    realEstate = null;
                } else {
                    textArea.appendText("...none...\n");
                }
            } else {
                textArea.appendText("User "+s+" doesn't have any property.\n");
            }

            textArea.appendText("---------------------------\n");
            textArea.appendText("---------------------------\n");
            textArea.appendText("---------------------------\n");
            textArea.appendText("---------------------------\n");
        }

    }

    public void start() {
        System.out.println("Starting "+ tName);
        if (printThread == null) {
            printThread = new Thread(this, tName);
            printThread.start();
        }
    }

    private void printFormula(Land land) {
        textArea.appendText("\tRegister number: "+land.getRegisterNum());
        textArea.appendText("\tAddress: "+land.getAddress());
        textArea.appendText("\tType: "+land.getTypeLand());
        textArea.appendText("\tArea: "+land.getArea());
        textArea.appendText("---------------------------");
    }

    private void printFormula(RealEstate realEstate) {
        textArea.appendText("\tRegister number: "+realEstate.getRegisterNum());
        textArea.appendText("\tAddress: "+realEstate.getAddress());
        textArea.appendText("\tType: "+realEstate.getTypeRealEstate());
        textArea.appendText("\tArea: "+realEstate.getArea());
        textArea.appendText("---------------------------");
    }
}
