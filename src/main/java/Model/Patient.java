package Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Patient {
    private long ID;
    private String fName;
    private String lName;
    private String gender;
    private String status;
    private java.sql.Date DOB;
    private String address;

    private long phone;
    private String s_phone;
    private boolean emptyPhone;

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDOB() {
        return DOB;
    }

    /*
             Date comes in as yyyy-MM-dd
    */
    public void setDOB(String DOB) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(DOB);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error in date format.");
        }
        this.DOB = new java.sql.Date(date.getTime());

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        if(s_phone.equals("")){
            System.err.println("Setting phone to empty");
            this.emptyPhone = true;
        }
        else{
            this.phone = Long.parseLong(s_phone);
        }
    }

    public boolean isEmptyPhone() {
        return emptyPhone;
    }

    public void setEmptyPhone(boolean emptyPhone) {
        this.emptyPhone = emptyPhone;
    }
}
