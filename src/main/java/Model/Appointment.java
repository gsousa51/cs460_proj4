package Model;
/*
    Class: Appointment
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in UPDATE/INSERT
    queries on the Appointment table.
    NOTE: Please consult the README.txt for what we're doing in the setters for Dates as well as the setters
    for String whose suffix is s_.

    Each field simply corresponds to a column in Appointment or is a flag/string rep for that column.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Appointment {
    //Appointment ID
    private long AID;
    //Doctor ID
    private long DID;
    private String s_DID;
    private boolean emptyDID;
    //Patient ID
    private long PID;
    //Reason for visit
    private String reason;
    //Various dates relating to Appointment
    private java.sql.Date apptDate;
    private java.sql.Date admission;
    private java.sql.Date expDischarge;
    private java.sql.Date actDischarge;
    private long room;
    private String s_room;
    private boolean emptyRoom;
    private String treatment;

    public long getAID() {
        return AID;
    }

    public void setAID(long AID) {
        this.AID = AID;
    }

    public long getDID() {
        return DID;
    }

    public void setS_DID(String s_DID){
        if(s_DID.equals("")){
            System.err.println("Setting DID to empty");
            this.emptyDID = true;
        }
        else{
            this.DID = Long.parseLong(s_DID);
        }
    }
    public void setDID(long DID) {
        this.DID = DID;
    }

    public long getPID() {
        return PID;
    }

    public void setPID(long PID) {
        this.PID = PID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getApptDate() {
        return apptDate;
    }


    /*
            Date comes in as yyyy-MM-dd
         */
    public void setApptDate(String apptDate) {

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            try {
                date = sdf1.parse(apptDate);
            } catch (ParseException e) {
                e.printStackTrace();
                System.err.println("Error in date format.");
            }
            this.apptDate = new java.sql.Date(date.getTime());

    }


    public Date getAdmission() {
        return this.admission;
    }

    /*
            Date comes in as yyyy-MM-dd
         */
    public void setAdmission(String admission) {
        if(admission.equals("")){
            this.admission = null;
            return;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(admission);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error in date format.");
        }
        this.admission = new java.sql.Date(date.getTime());

    }


    public Date getExpDischarge() {
        return this.expDischarge;
    }

    /*
            Date comes in as yyyy-MM-dd
         */
    public void setExpDischarge(String expDischarge) {
        if(expDischarge.equals("")){
            System.err.println("Rejected an empty date for expected discharge");
            this.expDischarge = null;
            return;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(expDischarge);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error in date format.");
        }
        this.expDischarge = new java.sql.Date(date.getTime());

    }

    public Date getActDischarge() {
        return this.actDischarge;
    }

    /*
            Date comes in as yyyy-MM-dd
         */
    public void setActDischarge(String actDischarge) {
        if(actDischarge.equals("")){
            System.err.println("Rejected an empty date for actual discharge");
            this.actDischarge = null;
            return;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(actDischarge);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error in date format.");
        }
        this.actDischarge = new java.sql.Date(date.getTime());

    }

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
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

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getS_DID() {
        return s_DID;
    }

    public boolean isEmptyDID() {
        return emptyDID;
    }

    public void setEmptyDID(boolean emptyDID) {
        this.emptyDID = emptyDID;
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
}
