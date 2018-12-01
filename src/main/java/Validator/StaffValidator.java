package Validator;

import Model.Doctor;
import Model.Staff;
import Model.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StaffValidator {

    private Staff staff;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public StaffValidator(Staff staff){
        this.staff = staff;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
    Status, dept_ID, office
     */
    public String createInsertMessage(){
        //TODO: Do we want to do this?
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.staff VALUES(";
        insertMessage = insertMessage.concat(staff.getID()+",");
        insertMessage = insertMessage.concat("'" + staff.getlName()+ "',");
        insertMessage = insertMessage.concat("'" + staff.getfName()+ "',");
        insertMessage = insertMessage.concat(staff.getDOB().getTime()+",");
        if(staff.isEmptySalary()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(staff.getSalary()+",");

        }
        if(staff.isEmptyDeptID()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(staff.getDeptID()+"");

        }
        if(staff.isEmptyOffice()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(staff.getOffice()+",");

        }
        if(!staff.getTitle().equals("")){
            insertMessage = insertMessage.concat(staff.getTitle());
        }
        else{
            insertMessage = insertMessage.concat("NULL");
        }


        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }

    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Staff \nSET ";

        if(!staff.getlName().equals("")){
            validUpdate = true;
            updateMessage = updateMessage.concat("L_Name = '" + staff.getlName()+"'");
            updateMessage = updateMessage.concat("\n");
        }
        if(!staff.getfName().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage =  updateMessage.concat("F_Name = '" + staff.getfName()+"'");
            updateMessage =  updateMessage.concat("\n");
            validUpdate  = true;
        }

        if(!staff.isEmptyDeptID()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Dept_ID = " + staff.getDeptID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!staff.isEmptyOffice()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Office = " + staff.getOffice());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }
        if(!staff.getTitle().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Title = " + staff.getTitle());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }
        updateMessage = updateMessage.concat(" WHERE NID = " + staff.getID() + "\n");
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
