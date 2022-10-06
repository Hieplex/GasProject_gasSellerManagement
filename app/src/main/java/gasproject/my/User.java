package gasproject.my;

public class User {
    private String Name;
    private long ID;
    private int PhoneNumber;
    private String Gender;
    private String Address;
    private String Gascanweight;
    private String GascanTrademark;

    public User(){
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int  getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getGascanweight() {
        return Gascanweight;
    }
    public void setGascanweight(String Gascanweight) {
        this.Gascanweight = Gascanweight;
    }

    public String getGascanTrademark() {
        return GascanTrademark;
    }

    public void setGascanTrademark(String GascanTrademark) {
        this.GascanTrademark = GascanTrademark;
    }

}
