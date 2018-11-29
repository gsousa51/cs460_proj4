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
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public DoctorValidator(Doctor doctor){
        this.doctor = doctor;
        this.valid = true;
        this.updateMessage = getUpdate();
        validate();

    }

    private String getUpdate(){
        String updateMessage = "UPDATE aswindle.Doctor \nSET ";
        if(doctor.getlName().equals("")){
            updateMessage = updateMessage.concat("L_Name = NULL");
        }
        else{
            updateMessage = updateMessage.concat("L_Name = '" + doctor.getlName()+"'");
        }
        updateMessage = updateMessage.concat(",\n");
        if(doctor.getfName().equals("")){
            updateMessage = updateMessage.concat("F_Name = NULL");
        }
        else{
            updateMessage =  updateMessage.concat("F_Name = '" + doctor.getfName()+"'");
        }
        updateMessage =  updateMessage.concat(",\n");
        if(doctor.getStatus().equals("")){
            updateMessage = updateMessage.concat("Status = NULL");
        }
        else{
            updateMessage = updateMessage.concat("Status = '" + doctor.getStatus() + "'");
        }
        updateMessage = updateMessage.concat(",\n");

        if(doctor.isEmptyDeptID()){
            updateMessage = updateMessage.concat("Dept_ID = NULL");
        }
        else{
            updateMessage = updateMessage.concat("Dept_ID = " + doctor.getDeptID());
        }
        updateMessage = updateMessage.concat(",\n");

        if(doctor.isEmptyOffice()){
            updateMessage = updateMessage.concat("Office = NULL");
        }
        else{
            updateMessage = updateMessage.concat("Office = " + doctor.getOffice());
        }
        updateMessage = updateMessage.concat("\n");

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

    public String getUpdateMessage() {
        return updateMessage;
    }
}
