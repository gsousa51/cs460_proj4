package Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Staff {
    private int ID;
    private String fName;
    private String lName;
    private java.sql.Date DOB;

    private int salary;
    private String s_salary;
    private boolean emptySalary;

    private int deptID;
    private String s_deptID;
    private boolean emptyDeptID;

    private int office;
    private String s_office;
    private boolean emptyOffice;

    private String title;

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

    public int getSalary() {
        return salary;
    }

    public void setS_salary(String s_salary){
        if(s_salary.equals("")){
            System.err.println("Setting salary to empty");
            this.emptySalary = true;
        }
        else{
            this.salary = Integer.parseInt(s_salary);
        }
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setS_deptID(String s_deptID){
        if(s_deptID.equals("")){
            System.err.println("Setting deptID to empty");
            this.emptyDeptID = true;
        }
        else{
            this.deptID = Integer.parseInt(s_deptID);
        }
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getS_salary() {
        return s_salary;
    }

    public boolean isEmptySalary() {
        return emptySalary;
    }

    public void setEmptySalary(boolean emptySalary) {
        this.emptySalary = emptySalary;
    }

    public String getS_deptID() {
        return s_deptID;
    }

    public boolean isEmptyDeptID() {
        return emptyDeptID;
    }

    public void setEmptyDeptID(boolean emptyDeptID) {
        this.emptyDeptID = emptyDeptID;
    }

    public String getS_office() {
        return s_office;
    }

    public boolean isEmptyOffice() {
        return emptyOffice;
    }

    public void setEmptyOffice(boolean emptyOffice) {
        this.emptyOffice = emptyOffice;
    }
}
