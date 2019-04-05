package OtherFunctionality;

import UserObject.User;

import java.io.*;
import java.util.HashMap;

public class SerializableUtility {
    //serializuje hashMapu
    public static void saveUsers(HashMap<String, User> usersData) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Database.out");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(usersData);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in Database.out");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //deserializuje hashMapu
    public static HashMap<String, User> loadUsers () {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Database.out"));
            HashMap<String, User> usersData = (HashMap<String, User>) in.readObject();
            in.close();
            return usersData;
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return null ;
    }
}
