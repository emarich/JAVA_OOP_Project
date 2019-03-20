package CadasterObject;

public class Cadastre {
    int updatedDate;

    private int numOfREOwners;
    private int numOfRealaEstate;

    Land land = new Land();
    LandOwner landOwner = new LandOwner();






    public void setNumOfREOwners(int numOfREOwners) {
        this.numOfREOwners = numOfREOwners;
    }

    public int getNumOfREOwners() {
        return numOfREOwners;
    }

    public void setNumOfRealEstate(int numOfRelaEstate) {
        this.numOfRealaEstate = numOfRelaEstate;
    }

    public int getNumOfRealEstate() {
        return numOfRealaEstate;
    }

}
