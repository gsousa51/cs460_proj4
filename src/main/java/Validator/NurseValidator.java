package Validator;

import Model.Doctor;
import Model.Nurse;
import Model.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NurseValidator {

    private Nurse nurse;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public NurseValidator(Nurse nurse){
        this.nurse = nurse;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
    Status, dept_ID, office
     */
    public String createInsertMessage(){
        //TODO: Do we want to do this?
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.nurse VALUES(";
        insertMessage = insertMessage.concat(nurse.getID()+",");
        insertMessage = insertMessage.concat("'" + nurse.getlName()+ "',");
        insertMessage = insertMessage.concat("'" + nurse.getfName()+ "',");
        insertMessage = insertMessage.concat(nurse.getDOB().getTime()+",");
        if(nurse.isEmptyRoom()){
            insertMessage = insertMessage.concat("NULL");
        }
        else{
            insertMessage = insertMessage.concat(""+nurse.getRoom());

        }
        if(nurse.isEmptyDeptID()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(nurse.getDeptID()+",");

        }

        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }

    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Nurse \nSET ";

        if(!nurse.getlName().equals("")){
            validUpdate = true;
            updateMessage = updateMessage.concat("L_Name = '" + nurse.getlName()+"'");
            updateMessage = updateMessage.concat("\n");
        }
        if(!nurse.getfName().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage =  updateMessage.concat("F_Name = '" + nurse.getfName()+"'");
            updateMessage =  updateMessage.concat("\n");
            validUpdate  = true;
        }

        if(!nurse.isEmptyDeptID()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Dept_ID = " + nurse.getDeptID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!nurse.isEmptyRoom()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Room = " + nurse.getRoom());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        updateMessage = updateMessage.concat(" WHERE NID = " + nurse.getID() + "\n");
        return updateMessage;
    }

    private void validUpdateate(){

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
