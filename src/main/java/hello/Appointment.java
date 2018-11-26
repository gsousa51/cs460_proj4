package hello;

public class Appointment {
    //Appointment ID
    private int AID;
    //Doctor ID
    private int DID;
    //Patient ID
    private int PID;
    private String reason;
    //TODO: Figure out how we want to handle this on the form side
    private String DOB;
    private String admission;
    private String expDischarge;
    private String actDischarge;
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getExpDischarge() {
        return expDischarge;
    }

    public void setExpDischarge(String expDischarge) {
        this.expDischarge = expDischarge;
    }

    public String getActDischarge() {
        return actDischarge;
    }

    public void setActDischarge(String actDischarge) {
        this.actDischarge = actDischarge;
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
