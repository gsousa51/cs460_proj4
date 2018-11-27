package hello;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Appointment {
    //Appointment ID
    private int AID;
    //Doctor ID
    private int DID;
    //Patient ID
    private int PID;
    private String reason;
    private java.sql.Date admission;
    private java.sql.Date expDischarge;
    private java.sql.Date actDischarge;
    private int room;
    private String treatment;

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public int getPID() {
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



    public Date getAdmission() {
        return this.admission;
    }

    /*
            Date comes in as yyyy-MM-dd
         */
    public void setAdmission(String admission) {

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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
