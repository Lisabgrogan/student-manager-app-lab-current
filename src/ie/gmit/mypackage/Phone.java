package ie.gmit.mypackage;

import java.io.Serializable;
import java.util.Date;

public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    // Instance Variables
    private String phoneId;
    private String phoneModel;
    private String phonePrice;

    // Constructors
    public Phone(String phoneId) {
        this.phoneId = phoneId;
    }

    public Phone(String phoneId, String phoneModel, String phonePrice) {
        // this(studentId); - could set studentId this way
        this.phoneId = phoneId;
        this.phoneModel = phoneModel;
        this.phoneModel = phonePrice;
    }

    public Phone(String phoneId, String phoneModel, String phonePrice, Date dob) {
        // this(phoneId); - could set phoneId this way
        this.phoneId = phoneId;
        this.phoneModel = phoneModel;
        this.phonePrice = phonePrice;

    }

    // Getters and Setters
    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(String phoneModel) {
        this.phoneModel = phoneModel;

    }
}
