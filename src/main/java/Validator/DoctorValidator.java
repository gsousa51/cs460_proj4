package Validator;
/*
    Class: DoctorValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used in DoctorController, and is used to validate/create
    INSERT and UPDATE queries on our Doctor Table.

    Public Methods:
        getUpdateMessage(), getInsertMessage()
        It used by DoctorController, found in Controller package.
        Descriptions for each can be found above each method.
 */
import Model.Doctor;

public class DoctorValidator {

    private Doctor doctor;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;
    public DoctorValidator(Doctor doctor){
        this.doctor = doctor;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
        Method uses the parameters stored in the Doctor object and creates an INSERT sql query
        to be used on the doctor table. Note that we only check if some values are empty
        since some are required to be entered by user on the Front-End.

        Return Val: An INSERT query for aswindle.Doctor.
     */
    public String createInsertMessage(){
        this.validInsert = true;
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
    /*
        Method takes fields from Doctor object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.Doctor.
     */
    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Doctor \nSET ";

        if(!doctor.getlName().equals("")){
            validUpdate = true;
            updateMessage = updateMessage.concat("L_Name = '" + doctor.getlName()+"'");
            updateMessage = updateMessage.concat("\n");
        }
        if(!doctor.getfName().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage =  updateMessage.concat("F_Name = '" + doctor.getfName()+"'");
            updateMessage =  updateMessage.concat("\n");
            validUpdate  = true;
        }

        if(!doctor.getStatus().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Status = '" + doctor.getStatus() + "'");
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!doctor.isEmptyDeptID()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Dept_ID = " + doctor.getDeptID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!doctor.isEmptyOffice()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Office = " + doctor.getOffice());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        updateMessage = updateMessage.concat(" WHERE DID = " + doctor.getID() + "\n");
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
