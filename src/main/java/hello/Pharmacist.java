package hello;

import java.sql.Date;

public class Pharmacist {
    private int ID;
    private String fName;
    private String lName;
    //TODO: Figure out how we want to handle this on the form side
    private String DOB;
    private int office;
    private int deptID;

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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }
}
