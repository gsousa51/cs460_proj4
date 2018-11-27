package hello;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Doctor {
    private int ID;
    private String fName;
    private String lName;
    private String status;
    private java.sql.Date DOB;
    private String address;

    private int phone;
    private String s_phone;
    private boolean emptyPhone;

    private int deptID;
    private String s_deptID;
    private boolean emptyDeptID;

    private int office;
    private String s_office;
    private boolean emptyOffice;

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

    public void setS_phone(String s_phone) {
        if(s_phone.equals("")){
            System.err.println("Set phone to empty");
            this.emptyPhone = true;
        }
        else{
            this.phone = Integer.parseInt(s_phone);
        }
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public void setS_deptID(String s_deptID) {
        if(s_deptID.equals("")){
            System.err.println("Set deptID to empty");
            this.emptyDeptID = true;
        }
        else{
            this.deptID = Integer.parseInt(s_deptID);
        }
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public void setS_office(String s_office){
        if(s_office.equals("")){
            System.err.println("Setting office to empty");
            this.emptyOffice = true;
        }
        else{
            this.office = Integer.parseInt(s_office);
        }
    }

    public String getS_phone() {
        return s_phone;
    }

    public boolean isEmptyPhone() {
        return emptyPhone;
    }

    public String getS_deptID() {
        return s_deptID;
    }

    public boolean isEmptyDeptID() {
        return emptyDeptID;
    }

    public String getS_office() {
        return s_office;
    }

    public boolean isEmptyOffice() {
        return emptyOffice;
    }
}
