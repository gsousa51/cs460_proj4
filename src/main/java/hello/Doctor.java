package hello;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Doctor {
    private int ID;
    private String fName;
    private String lName;
    private String status;
    //TODO: Figure out how we want to handle this on the form side
    private java.sql.Date DOB;
    private String address;
    private int phone;

    public int getID() {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
