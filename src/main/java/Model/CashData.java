package Model;
/*
    Class: CashData
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a "Bean" for the values the user wants to use in UPDATE/INSERT
    queries on the Cash_Data table.
    NOTE: Please consult the README.txt for what we're doing in the setters for Dates as well as the setters
    for String whose suffix is s_.

    Each field simply corresponds to a column in Cash_Data or is a flag/string rep for that column.

    The only methods in this class are Getters/Setters used primarily by .html forms to store values
    or display values, or this classes corresponding Controller found in the Controller package.
 */
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CashData {
    private long xactID;
    private long EID;
    private String s_EID;
    private boolean emptyEID;

    private long PID;

    private long amount;
    private String s_amount;
    private boolean emptyAmount;

    private java.sql.Date dueDate;
    private String status;
    private java.sql.Date paidDate;

    public long getXactID() {
        return xactID;
    }

    public void setXactID(long xactID) {
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
            this.EID = Long.parseLong(s_EID);
        }
    }

    public long getEID() {
        return EID;
    }

    public void setEID(String emptyEID){
        System.err.println("Set EID to be empty");
        this.emptyEID = true;
    }
    public void setEID(long EID) {
        this.EID = EID;
    }

    public long getPID() {
        return PID;
    }

    public void setPID(long PID) {
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
            this.amount = Long.parseLong(s_amount);
        }
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(String emptyAmount){
        System.err.println("Set amount to be empty");
        this.emptyAmount = true;
    }
    public void setAmount(long amount) {
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
