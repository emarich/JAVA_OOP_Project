package CadasterObjects;

import OtherFunctionality.AddressFormatException;
import com.sun.deploy.util.ArrayUtil;

import java.io.Serializable;

public class Address {
    public static void correctAddress(String address) throws AddressFormatException { //checks, if address is in correct format
        String[] outputAddress = new String[3];
        try {
            boolean bool = false;
            outputAddress = address.split(","); //splits string to street, city and state string

            for (int i = 0; i < 3; i++) {
                if (outputAddress[i] == null) { //if there is nothing in one of the strings
                    throw new AddressFormatException("You must enter street, city and state");
                }

                //checks if the characters is a letter or number, if it is letter, it will return true
                for (int j = 0; j < outputAddress[i].length(); j++) {
                    if (Character.isLetter(outputAddress[i].charAt(j)) ||
                        Character.isDigit(outputAddress[i].charAt(j))) {
                        bool = true;
                    }
                }

                if (!bool) {
                    throw new AddressFormatException("You must enter street, city and state");
                }
            }
            outputAddress = null;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AddressFormatException("Please, write it in this format:\n(example) Hlavna 1, 801 01 Bratislava, Slovensko");
        }
    }

    public static String setPerfectAddress(String address) {
        String finalAddress = ""; //whole address
        String finalElement = ""; //one element of the address

        String[] outputAddress = new String[3];
        outputAddress = address.split(",", 3); //splits string to street, city and state string

        for(int x = 0; x < outputAddress.length; ++x) { //or x<3

            String[] element = outputAddress[x].split("\\s+"); //get element in array
            int count = 0;

            for(String s : element) { //first letter to upper case
                if (s.matches(".*\\d.*\\s[^\\w]") || s.equals("")) { //if is it number or non-word char, break
                    ++count;
                    continue;
                }
                element[count] = s.substring(0, 1).toUpperCase() + s.substring(1);
                ++count;
            }

            for(int i = 0; i < element.length; i++) {
                if (i == element.length-1) {
                    finalElement = finalElement + element[i];
                    break;
                }

                finalElement = finalElement + element[i]+" ";
            }

            if (x == outputAddress.length-1) { //or x==2
                finalAddress = finalAddress + finalElement;
                break;
            }

            finalAddress = finalAddress + finalElement+",";
            finalElement = "";
        }

        return  finalAddress;
    }
}
