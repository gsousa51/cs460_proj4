package Validator;

import Model.Doctor;
import Model.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoctorValidator {

    private Doctor doctor;
    private boolean valid;
    private String updateMessage;
    private String insertMessage;
    public DoctorValidator(Doctor doctor){
        this.doctor = doctor;
        this.valid = false;

        validate();

    }

    /*
    Status, dept_ID, office
     */
    public String getInsertMessage(){
        //TODO: Do we want to do this?
        this.valid = true;
        String insertMessage = "INSERT INTO aswindle.doctor VALUES(";
        insertMessage = insertMessage.concat(doctor.getID()+",");
        insertMessage = insertMessage.concat("'" + doctor.getlName()+ "',");
        insertMessage = insertMessage.concat("'" + doctor.getfName()+ "',");
        insertMessage = insertMessage.concat(doctor.getDOB().getTime()+",");
        if(doctor.getStatus().equals("")){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat("'" + doctor.getStatus()+"',");

        }
        if(doctor.isEmptyDeptID()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(doctor.getDeptID()+",");

        }
        if(doctor.isEmptyOffice()){
            insertMessage = insertMessage.concat("NULL");
        }
        else{
            insertMessage = insertMessage.concat(""+doctor.getOffice());

        }
        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }

    public String getUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Doctor \nSET ";

        if(!doctor.getlName().equals("")){
            valid = true;
            updateMessage = updateMessage.concat("L_Name = '" + doctor.getlName()+"'");
            updateMessage = updateMessage.concat("\n");
        }
        if(!doctor.getfName().equals("")){
            if(valid){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage =  updateMessage.concat("F_Name = '" + doctor.getfName()+"'");
            updateMessage =  updateMessage.concat("\n");
            valid  = true;
        }

        if(!doctor.getStatus().equals("")){
            if(valid){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Status = '" + doctor.getStatus() + "'");
            updateMessage = updateMessage.concat("\n");
            valid = true;
        }

        if(!doctor.isEmptyDeptID()){
            if(valid){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Dept_ID = " + doctor.getDeptID());
            updateMessage = updateMessage.concat("\n");
            valid = true;
        }

        if(!doctor.isEmptyOffice()){
            if(valid){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Office = " + doctor.getOffice());
            updateMessage = updateMessage.concat("\n");
            valid = true;
        }

        updateMessage = updateMessage.concat(" WHERE DID = " + doctor.getID() + "\n");
        return updateMessage;
    }

    private void validate(){

    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

}
