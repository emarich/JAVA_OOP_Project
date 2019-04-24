package OtherFunctionality;

import CadasterObjects.CadasterObject;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import Owners.City;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
import ViewContollers.RegisterController;
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
                    textArea.appendText("Error: object Owner has different instance\n");
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

    private void printFormula(CadasterObject cadasterObject) {
        if (cadasterObject instanceof Land) {
            textArea.appendText("\tRegister number: "+((Land)cadasterObject).getRegisterNum()+"\n");
            textArea.appendText("\tAddress: "+((Land)cadasterObject).getAddress()+"\n");
            textArea.appendText("\tType: "+((Land)cadasterObject).getTypeLand()+"\n");
            textArea.appendText("\tArea: "+((Land)cadasterObject).getArea()+"\n");
            textArea.appendText("\t---------------------------\n");
        } else if (cadasterObject instanceof RealEstate) {
            textArea.appendText("\tRegister number: "+((RealEstate)cadasterObject).getRegisterNum()+"\n");
            textArea.appendText("\tAddress: "+((RealEstate)cadasterObject).getAddress()+"\n");
            textArea.appendText("\tType: "+((RealEstate)cadasterObject).getTypeRealEstate()+"\n");
            textArea.appendText("\tArea: "+((RealEstate)cadasterObject).getArea()+"\n");
            textArea.appendText("\t---------------------------\n");
        }

    }

}
