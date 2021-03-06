package Model;
/*
    Class: Patient
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in UPDATE/INSERT
    queries on the Patient table.
    NOTE: Please consult the README.txt for what we're doing in the setters for Dates as well as the setters
    for String whose suffix is s_.

    Each field simply corresponds to a column in Patient or is a flag/string rep for that column.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
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

    public void setID(long ID) {
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

    public void setPhone(long phone) {
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
