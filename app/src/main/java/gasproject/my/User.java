package gasproject.my;

import android.media.Image;
import android.widget.ImageView;

public class User {
    private String Name;
    private long ID;
    private int PhoneNumber;
    private String Gender;
    private String Address;
    private String Gastrademark;
    private String Gasproduct;
    private ImageView img;

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


    public String  getGastrademark() {
        return Gastrademark;
    }

    public void setGastrademark(String Gastrademark) {
        this.Gastrademark = Gastrademark;
    }

    public String getGasproduct() {
        return Gasproduct;
    }

    public void setGasproduct(String Gasproduct) {
        this.Gasproduct = Gasproduct;
    }



}
