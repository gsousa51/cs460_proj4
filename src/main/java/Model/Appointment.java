package Model;

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
    private String reason;
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

    public void setAID(int AID) {
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
    public void setDID(int DID) {
        this.DID = DID;
    }

    public long getPID() {
        return PID;
    }

    public void setPID(int PID) {
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

    public void setRoom(int room) {
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
