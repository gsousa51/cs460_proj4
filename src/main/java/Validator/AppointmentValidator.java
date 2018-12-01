package Validator;

import Model.Doctor;
import Model.Appointment;
import Model.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppointmentValidator {

    private Appointment appointment;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public AppointmentValidator(Appointment appointment){
        this.appointment = appointment;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
    Status, dept_ID, office
     */
    public String createInsertMessage(){
        //TODO: Do we want to do this?
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.appointment VALUES(";
        insertMessage = insertMessage.concat(appointment.getAID()+",");
        insertMessage = insertMessage.concat(appointment.getPID()+",");
        insertMessage = insertMessage.concat(appointment.getDID()+",");
        insertMessage = insertMessage.concat("'" + appointment.getReason() +"',");
        insertMessage = insertMessage.concat(appointment.getApptDate().getTime() +",");

        if(appointment.getAdmission() != null){
            insertMessage = insertMessage.concat(appointment.getAdmission().getTime() +",");
        }
        else{
            insertMessage = insertMessage.concat("NULL,");
        }

        if(appointment.getExpDischarge() != null){
            insertMessage = insertMessage.concat(appointment.getExpDischarge().getTime() +",");
        }
        else{
            insertMessage = insertMessage.concat("NULL,");
        }

        if(appointment.getActDischarge() != null){
            insertMessage = insertMessage.concat(appointment.getActDischarge().getTime() +",");
        }
        else{
            insertMessage = insertMessage.concat("NULL,");
        }

        if(!appointment.isEmptyRoom()){
            insertMessage = insertMessage.concat(appointment.getRoom() +",");
        }
        else{
            insertMessage = insertMessage.concat("NULL,");
        }

        if(!appointment.getTreatment().equals("")){
            insertMessage = insertMessage.concat("'" + appointment.getTreatment()+ "'");
        }
        else{
            insertMessage = insertMessage.concat("NULL");
        }
        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }

    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Appointment \nSET ";


        if(!appointment.isEmptyDID()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("DID = " + appointment.getDID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!(appointment.getAdmission() != null)){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Admission = " + appointment.getAdmission().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!(appointment.getActDischarge() != null)){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Act_Discharge = " + appointment.getActDischarge().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!(appointment.getExpDischarge() != null)){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Exp_Discharge = " + appointment.getExpDischarge().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }
        if(!appointment.isEmptyRoom()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Room = " + appointment.getRoom());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!appointment.getTreatment().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Treatment = '" + appointment.getTreatment() + "'");
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }
        updateMessage = updateMessage.concat(" WHERE AID = " + appointment.getAID() + "\n");
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
