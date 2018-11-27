package hello;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CashData {
    private int xactID;
    private int EID;
    private String s_EID;
    private int PID;
    private int amount;
    private String s_amount;
    private boolean emptyAmount;
    private boolean emptyEID;
    private java.sql.Date dueDate;
    private String status;
    private java.sql.Date paidDate;

    public int getXactID() {
        return xactID;
    }

    public void setXactID(int xactID) {
        this.xactID = xactID;
    }

    public String getS_EID() {
        return s_EID;
    }

    public void setS_EID(String s_EID) {
        if(s_EID.equals("")){
            System.err.println("Set EID to be empty");
            this.emptyEID = true;
        }
        else{
            this.EID = Integer.parseInt(s_EID);
        }
    }

    public int getEID() {
        return EID;
    }

    public void setEID(String emptyEID){
        System.err.println("Set EID to be empty");
        this.emptyEID = true;
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

    public String getS_amount() {
        return s_amount;
    }

    public void setS_amount(String s_amount) {
        if(s_amount.equals("")){
            System.err.println("Set amount to be empty");
            this.emptyAmount = true;
        }
        else{
            this.amount = Integer.parseInt(s_amount);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(String emptyAmount){
        System.err.println("Set amount to be empty");
        this.emptyAmount = true;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    /*
            Date comes in as yyyy-MM-dd
         */
    public void setDueDate(String dueDate) {
        if(dueDate.equals("")){
            System.err.println("Rejected empty value for dueDate");
            return;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error in date format.");
        }
        this.dueDate = new java.sql.Date(date.getTime());

    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    /*
            Date comes in as yyyy-MM-dd
         */
    public void setPaidDate(String paidDate) {
        if(paidDate.equals("")){
            System.err.println("Rejected empty value for paidDate");
            return;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(paidDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error in date format.");
        }
        this.paidDate = new java.sql.Date(date.getTime());

    }

    public boolean isEmptyAmount() {
        return emptyAmount;
    }

    public boolean isEmptyEID() {
        return emptyEID;
    }
}
