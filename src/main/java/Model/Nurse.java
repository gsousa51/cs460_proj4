package Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Nurse {
    private int ID;
    private String fName;
    private String lName;
    private java.sql.Date DOB;

    private long room;
    private String s_room;
    private boolean emptyRoom;

    private long deptID;
    private String s_deptID;
    private boolean emptyDeptID;

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
    public void setRoom(int room) {
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

    public void setDeptID(int deptID) {
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
