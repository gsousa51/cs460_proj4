package Validator;

import Model.Appointment;

public class AppointmentValidator {

    private Appointment appointment;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public AppointmentValidator(Appointment appointment){
        this.appointment = appointment;
        //Need to make sure the dates are valid for an insertion statement.
        this.validInsert = validateInsertDates();
        this.updateMessage = createUpdateMessage();
    }
//TODO: We need to validate the admission dates...
    /*
    Status, dept_ID, office
     */
    public String createInsertMessage(){

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

        if(!appointment.getTreatment().equals("empty")){
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

        if(appointment.getAdmission() != null){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Admission = " + appointment.getAdmission().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(appointment.getActDischarge() != null){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Act_Discharge = " + appointment.getActDischarge().getTime());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(appointment.getExpDischarge() != null){
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

        if(!appointment.getTreatment().equals("empty")){
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

    private boolean validateInsertDates(){
        //Check if we admitted the patient. If so, this can't be before the appointment date
        //Or after the expected/actual discharges.
        if(appointment.getAdmission() != null){
            if(appointment.getAdmission().getTime() < appointment.getApptDate().getTime()){
                return false;
            }
            if(appointment.getActDischarge() != null) {
                if (appointment.getActDischarge().getTime() < appointment.getAdmission().getTime()) {
                    return false;
                }
            }
            if(appointment.getExpDischarge() != null){
                if(appointment.getExpDischarge().getTime() < appointment.getAdmission().getTime()) {
                    return false;
                }
            }
        }
        else{
            //Exp discharge can't be before the appointment date
            if(appointment.getExpDischarge() != null){
                if(appointment.getExpDischarge().getTime() < appointment.getApptDate().getTime()){
                    return false;
                }
            }
            //Neither can the actual discharge
            if(appointment.getActDischarge() != null){
                if(appointment.getActDischarge().getTime() < appointment.getApptDate().getTime()){
                    return false;
                }
            }
        }

        //If we got here, this is a valid insertion.
        return true;
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
