package OtherFunctionality;

import CadasterObjects.CadasterObject;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import Owners.City;
import Owners.Owner;
import Owners.Ownership;
import UserObject.Database;
import UserObject.User;
import javafx.scene.control.TextArea;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;

public class PrintCadastre implements Serializable {

    private Database usersDatabase;
    private DataObserver textArea;
    private TextArea basicTextArea;
    private volatile boolean running;
    private Ownership owner;
    private User user;

    public PrintCadastre (Database database, DataObserver textArea1) {
        usersDatabase = database;
        textArea = textArea1;
        running = true;
    }

    public PrintCadastre(User user, TextArea textArea1) {
        this.user = user;
        basicTextArea = textArea1;
        running = true;
    }


    public void setTextArea(DataObserver textArea) {
        this.textArea = textArea;
    }

    public void print() {
        while (running) {
            try {
                for (String s : usersDatabase.getUsersDataHM().keySet()) {
                    owner = usersDatabase.getUser(s).getOwner();

                    textArea.appendText("\n|| Username: " + s + " \n");

                    if (usersDatabase.getUser(s).getIsOwner()) {
                        printOwnerWithProperty(owner);
                    } else {
                        textArea.appendText("User " + s + " doesn't have any property.\n");
                    }

                    textArea.appendText("-------------------------------\n");
                    textArea.appendText("-------------------------------\n");
                    textArea.appendText("-------------------------------\n");
                    textArea.appendText("-------------------------------\n");
                }

                running = false;

            } catch (Exception e) {
                //TRYING
                running = false;
                textArea.setText("");
                System.out.println("||||Whole running thread catching expression");
                //this.start();
            } catch (Throwable throwable) {
                //TRYING
                running = false;
                textArea.setText("");
                System.out.println("||||Whole running thread catching throwable");
            }
        }
    }

    private void printFormula(CadasterObject cadasterObject, TextArea textArea) throws NullPointerException {
        try {
            if (cadasterObject == null) {
                throw  new NullPointerException();
            }
            if (cadasterObject instanceof Land) {
                textArea.appendText("\tRegister number: "+((Land)cadasterObject).getRegisterNum()+"\n");
                textArea.appendText("\tAddress: "+((Land)cadasterObject).getAddress()+"\n");
                textArea.appendText("\tType: "+((Land)cadasterObject).getTypeLand()+"\n");
                textArea.appendText("\tArea: "+((Land)cadasterObject).getArea()+" m2\n");
                textArea.appendText("\tLand form: "+((Land)cadasterObject).getLandform().getNum()+"\n");
                textArea.appendText("\t-----------------------------\n");
            } else if (cadasterObject instanceof RealEstate) {
                textArea.appendText("\tRegister number: "+((RealEstate)cadasterObject).getRegisterNum()+"\n");
                textArea.appendText("\tAddress: "+((RealEstate)cadasterObject).getAddress()+"\n");
                textArea.appendText("\tType: "+((RealEstate)cadasterObject).getTypeRealEstate()+"\n");
                textArea.appendText("\tArea: "+((RealEstate)cadasterObject).getArea()+" m2\n");
                textArea.appendText("\t-----------------------------\n");
            }
        } catch (Exception e) {
            //TRYING
            running = false;
            textArea.setText("");
            System.out.println("||||Printing catching expression");
            running = false;
        }

    }

    public void printUserProperty() {
        try {
            basicTextArea.appendText("\n|| Username: " + user.getUsername() + " \n");

            if (user.getIsOwner()) {
                printOwnerWithProperty(owner);
            } else {
                basicTextArea.appendText("User " + user.getUsername() + " doesn't have any property.\n");
            }

            basicTextArea.appendText("-------------------------------\n");
            basicTextArea.appendText("-------------------------------\n");
            basicTextArea.appendText("-------------------------------\n");
            basicTextArea.appendText("-------------------------------\n");

            running = false;

            } catch (Exception e) {
                e.printStackTrace();
                running = false;
                basicTextArea.setText("");
                System.out.println("||||Whole running thread catching expression");
            }
        }

        private void printOwnerWithProperty(Ownership owner) {
            owner = user.getOwner();
            if (owner instanceof Owner) {
                basicTextArea.appendText("|| Name: " + ((Owner) owner).getName() + "\n");
                basicTextArea.appendText("|| Mutual address: " + ((Owner) owner).getMutualAddress() + "\n");
            } else if (owner instanceof City) {
                basicTextArea.appendText("|| Phone number: " + ((City) owner).getPhoneNumber() + "\n");
                basicTextArea.appendText("|| Email: " + ((City) owner).getEmail() + "\n");
            } else {
                basicTextArea.appendText("Error: object Owner has different instance\n");
            }

            basicTextArea.appendText("LANDS:-------------------------\n");
            if (owner.getHaveLand()) {
                Land land;
                for (int i = 0; i < owner.getOwnedLands().size(); i++) {
                    land = owner.getOwnedLands().get(i);
                    if (land == null) {
                        continue;
                    }
                    printFormula(land, basicTextArea);
                }
                land = null;
            } else {
                textArea.appendText("...none...\n");
            }

            basicTextArea.appendText("REAL ESTATES:------------------\n");
            if (owner.getHaveRealEstate()) {
                RealEstate realEstate;
                for (int i = 0; i < owner.getOwnedRE().size(); i++) {
                    realEstate = owner.getOwnedRE().get(i);
                    if (realEstate == null) {
                        continue;
                    }
                    printFormula(realEstate, basicTextArea);
                }
                realEstate = null;
            } else {
                basicTextArea.appendText("...none...\n");
            }
        }

}
