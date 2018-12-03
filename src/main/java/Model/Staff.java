package Model;

/*
    Class: Staff
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in UPDATE/INSERT
    queries on the Staff table.
    NOTE: Please consult the README.md for what we're doing in the setters for Dates as well as the setters
    for String whose suffix is s_.

    Each field simply corresponds to a column in Staff or is a flag/string rep for that column.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Staff {
    private long ID;
    private String fName;
    private String lName;
    private java.sql.Date DOB;

    private long salary;
    private String s_salary;
    private boolean emptySalary;

    private long deptID;
    private String s_deptID;
    private boolean emptyDeptID;

    private long office;
    private String s_office;
    private boolean emptyOffice;

    private String title;

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

    public long getSalary() {
        return salary;
    }

    public void setS_salary(String s_salary){
        if(s_salary.equals("")){
            System.err.println("Setting salary to empty");
            this.emptySalary = true;
        }
        else{
            this.salary = Long.parseLong(s_salary);
        }
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getDeptID() {
        return deptID;
    }

    public void setS_deptID(String s_deptID){
        if(s_deptID.equals("")){
            System.err.println("Setting deptID to empty");
            this.emptyDeptID = true;
        }
        else{
            this.deptID = Long.parseLong(s_deptID);
        }
    }

    public void setDeptID(long deptID) {
        this.deptID = deptID;
    }

    public long getOffice() {
        return office;
    }

    public void setOffice(long office) {
        this.office = office;
    }

    public void setS_office(String s_office){
        if(s_office.equals("")){
            System.err.println("Setting office to empty");
            this.emptyOffice = true;
        }
        else{
            this.office = Long.parseLong(s_office);
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
