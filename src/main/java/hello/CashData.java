package hello;

public class CashData {
    private int xactID;
    private int EID;
    private int PID;
    private int amount;
    //TODO: Figure out how to handle dates
    private String due;
    private String status;
    private String paidDate;

    public int getXactID() {
        return xactID;
    }

    public void setXactID(int xactID) {
        this.xactID = xactID;
    }

    public int getEID() {
        return EID;
    }

    public void setEID(int EID) {
        this.EID = EID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }
}
