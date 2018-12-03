package Validator;
/*
    Class: CashDataValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used in CashDataController, and is used to validate/create
    INSERT and UPDATE queries on our CashData Table.

    Public Methods:
        getInsertMessage(), getUpdateMessage()
        Both are used by CashDataController, found in Controller package.
        Descriptions for each can be found above each method.
 */
import Model.CashData;

public class CashDataValidator {

    private CashData cashData;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public CashDataValidator(CashData cashData){
        this.cashData = cashData;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
        Method uses the parameters stored in the CashData object and creates an INSERT sql query
        to be used on the Cash_Data table. Note that we only check if some values are empty
        since some are required to be entered by user on the Front-End.

        Return Val: An INSERT query for aswindle.Cash_Data.
     */
    public String createInsertMessage(){
        //There will never be invalid inserts for this since our Front-End validates for us.
        //However, we left this here for possible modifications in the future.
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.cash_Data VALUES(";
        insertMessage = insertMessage.concat(cashData.getXactID()+",");
        insertMessage = insertMessage.concat(cashData.getEID()+",");
        insertMessage = insertMessage.concat(cashData.getPID()+",");
        insertMessage = insertMessage.concat(cashData.getAmount() +",");
        insertMessage = insertMessage.concat(cashData.getDueDate().getTime() +",");
        insertMessage = insertMessage.concat("'" + cashData.getStatus() +"',");

        if(cashData.getPaidDate() != null){
            insertMessage = insertMessage.concat(cashData.getPaidDate().getTime() + "");
        }
        else{
            insertMessage = insertMessage.concat("NULL");
        }
        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }
    /*
        Method takes fields from CashData object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.Cash_Data.
     */
    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Cash_Data \nSET ";


        if(!cashData.isEmptyEID()){
            updateMessage = updateMessage.concat("EID = " + cashData.getEID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!cashData.isEmptyAmount()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Amount = " + cashData.getAmount());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(cashData.getDueDate() != null){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Due = " + cashData.getDueDate().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!cashData.getStatus().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Status = '" + cashData.getStatus() + "'");
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(cashData.getPaidDate() != null){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Paid = " + cashData.getPaidDate().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        updateMessage = updateMessage.concat(" WHERE Xact_ID = " + cashData.getXactID() + "\n");
        return updateMessage;
    }


    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public String getInsertMessage() {
        return createInsertMessage();
    }

    public void setInsertMessage(String insertMessage) {
        this.insertMessage = insertMessage;
    }

    public boolean isValidUpdate() {
        return validUpdate;
    }

    public void setValidUpdate(boolean validUpdate) {
        this.validUpdate = validUpdate;
    }

    public boolean isValidInsert() {
        return validInsert;
    }

    public void setValidInsert(boolean validInsert) {
        this.validInsert = validInsert;
    }
}
