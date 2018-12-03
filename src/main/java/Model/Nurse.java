package Model;

/*
    Class: Nurse
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in UPDATE/INSERT
    queries on the Nurse table.
    NOTE: Please consult the README.md for what we're doing in the setters for Dates as well as the setters
    for String whose suffix is s_.

    Each field simply corresponds to a column in Nurse or is a flag/string rep for that column.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Nurse {
    private long ID;
    private String fName;
    private String lName;
    private java.sql.Date DOB;

    private long room;
    private String s_room;
    private boolean emptyRoom;

    private long deptID;
    private String s_deptID;
    private boolean emptyDeptID;

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

    public long getRoom() {
        return room;
    }
    public void setS_room(String s_room){
        if(s_room.equals("")){
            System.err.println("Setting room to empty");
            this.emptyRoom = true;
        }
        else{
            this.room = Long.parseLong(s_room);
        }
    }
    public void setRoom(long room) {
        this.room = room;
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

    public String getS_room() {
        return s_room;
    }

    public boolean isEmptyRoom() {
        return emptyRoom;
    }

    public void setEmptyRoom(boolean emptyRoom) {
        this.emptyRoom = emptyRoom;
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
}
