package gasproject.my;

import android.media.Image;
import android.widget.ImageView;

public class User {
    private String Name;
    private long ID;
    private int PhoneNumber;
    private String Gender;
    private String Address;
    private String Gascanweight;
    private String GascanTrademark;
    private String GascanColor;
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
    public String getGascanColor() {
        return GascanColor;
    }
    public void setGascanColor(String GascanColor) {
        this.GascanColor = GascanColor;
    }

    public ImageView getImage() {
        return img;
    }

    public void setImage(ImageView img) {
        this.img = img;
    }

}
