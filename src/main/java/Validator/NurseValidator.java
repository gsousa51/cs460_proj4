package Validator;
/*
    Class: NurseValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used in DoctorController, and is used to validate/create
    INSERT and UPDATE queries on our Doctor Table.

    Public Methods:
        getUpdateMessage(), getInsertMessage()
        It used by NurseController, found in Controller package.
        Descriptions for each can be found above each method.
 */
import Model.Nurse;

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
        Method uses the parameters stored in the Nurse object and creates an INSERT sql query
        to be used on the nurse table. Note that we only check if some values are empty
        since some are required to be entered by user on the Front-End.

        Return Val: An INSERT query for aswindle.Nurse.
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
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(nurse.getRoom()+",");

        }
        if(nurse.isEmptyDeptID()){
            insertMessage = insertMessage.concat("NULL");
        }
        else{
            insertMessage = insertMessage.concat(nurse.getDeptID()+"");

        }

        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }
    /*
        Method takes fields from Nurse object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.Nurse.
     */
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
