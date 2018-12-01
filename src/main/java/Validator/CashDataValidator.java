package Validator;

import Model.Doctor;
import Model.CashData;
import Model.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    //TODO: We need to validate the admission dates...
    /*
    Status, dept_ID, office
     */
    public String createInsertMessage(){
        //TODO: Do we want to do this?
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.cashData VALUES(";
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
            updateMessage = updateMessage.concat("Status = " + cashData.getStatus());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }
        if(cashData.getPaidDate() != null){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Paid = " + cashData.getDueDate().getTime());
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
