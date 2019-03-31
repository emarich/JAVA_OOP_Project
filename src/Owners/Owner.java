package Owners;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Owner implements Serializable {
    private String username;
    private String name;
    private Date birthDate;
    DateFormat dateFormat = new SimpleDateFormat("dd.mm.yy");
    //Date date = DATE_FORMAT.parse("2013-12-4"); -> date.toString
    private Address mutualAddress;
    private String[] outputAddress = new String[3];
    private String phoneNumber;
    private String email;


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setBirthDate(String date) {
        try {
            birthDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public String getBirthDate() {
        return dateFormat.format(birthDate);
    }

    public void setMutualAddress(String address) {
        try {
            outputAddress = address.split(",");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format must be: /n (example) Hlavna 1, 801 01 Bratislava, Slovensko");
        }
        mutualAddress.setStreetAndNum(outputAddress[0]);
        mutualAddress.setCity(outputAddress[1]);
        mutualAddress.setState(outputAddress[2]);
    }
    public Address getMutualAddress() {
        return mutualAddress;
    }



    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }















    public void emailCheck(String usersEmail) {
        int length = usersEmail.length();
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
        boolean flag7 = false;
        int count = 0;


        //Condition 1
        if((length>3 && length<60))
            flag1 = true;
        else
            flag1 = false;

        //Condition 2
        int temp = email.length();
        if(email.contains("@")){
            flag2=true;
            int a=email.indexOf("@");
            for(int i=a;i<temp;i++){
                if(email.charAt(i)=='.'){
                    flag3=true;
                    count=count+1;
                }
            }
            if(count<1||count>2){
                flag4=false;
            }
            else{
                flag4=true;
            }
        }
        else{
            flag2 =false;
            System.out.println("No @ symbol present");
        }


        //Condition 3
        if(email.matches("[A-Z a-z _]+@.*")) //Unable to get the right RegEx here!
            flag5 = true;
        else
            flag5 = false;

        //Condition4
        if(Character.isUpperCase(email.charAt(0))==true)
            flag6 = true;
        else
            flag6=false;

        if(flag1==true && flag2==true && flag3==true && flag4==true && flag5==true &&flag6==true)
            System.out.println("Email ID is valid");
        else{
            if(flag1==false)
                System.out.println("Inavlid length of Email ID");
            if(flag2==false||flag3==false||flag4==false)
                System.out.println("Invalid Position of Special Characters");
            if(flag5==false)
                System.out.println("Invalid combination for username");
            if(flag6==false)
                System.out.println("Invalid case of first letter");
        }
    }
}
