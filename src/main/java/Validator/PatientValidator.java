package Validator;

import Model.Patient;

public class PatientValidator {

    private Patient patient;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public PatientValidator(Patient patient){
        this.patient = patient;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
    Status, dept_ID, office
     */
    public String createInsertMessage(){
        //TODO: Do we want to do this?
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.patient VALUES(";
        insertMessage = insertMessage.concat(patient.getID()+",");
        insertMessage = insertMessage.concat("'" + patient.getlName()+ "',");
        insertMessage = insertMessage.concat("'" + patient.getfName()+ "',");
        if(patient.getGender().equals("")){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat("'" + patient.getGender()+"',");
        }
        insertMessage = insertMessage.concat(patient.getDOB().getTime()+",");
        if(patient.getAddress().equals("")){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat("'" + patient.getAddress()+"',");

        }
        if(patient.isEmptyPhone()){
            insertMessage = insertMessage.concat("NULL");
        }
        else{
            insertMessage = insertMessage.concat(patient.getPhone()+"");
        }

        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }

    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Patient \nSET ";

        if(!patient.getlName().equals("")){
            validUpdate = true;
            updateMessage = updateMessage.concat("L_Name = '" + patient.getlName()+"'");
            updateMessage = updateMessage.concat("\n");
        }
        if(!patient.getfName().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage =  updateMessage.concat("F_Name = '" + patient.getfName()+"'");
            updateMessage =  updateMessage.concat("\n");
            validUpdate  = true;
        }

        if(!patient.getGender().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Gender = '" + patient.getGender()+"'");
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!patient.getAddress().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Address = '" + patient.getAddress() + "'");
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!patient.isEmptyPhone()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Phone = " + patient.getPhone());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }


        updateMessage = updateMessage.concat(" WHERE PID = " + patient.getID() + "\n");
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
