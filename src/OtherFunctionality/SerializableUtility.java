package OtherFunctionality;

import UserObject.Database;
import UserObject.User;

import java.io.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class SerializableUtility {
    //serializing hash map
    public static void saveUsers(HashMap<String, User> usersData) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Database.out");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(usersData);
            out.close();
            fileOut.close();
            System.out.println("Saving in Database.out");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //deserializing hash map
    public static HashMap<String, User> loadUsers () {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Database.out"));
            HashMap<String, User> usersData = (HashMap<String, User>) in.readObject();
            in.close();
            System.out.println("Loading from Database.out");
            return usersData;
        } catch (IOException | ClassNotFoundException i) {
            try {
                new FileWriter("Database.out", false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i.printStackTrace();
        }
        return null ;
    }
}
