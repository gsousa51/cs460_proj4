package Validator;
/*
    Class: StaffValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used in StaffController, and is used to validate/create
    INSERT and UPDATE queries on our Staff Table.

    Public Methods:
        getUpdateMessage(), getInsertMessage()
        It used by StaffController, found in Controller package.
        Descriptions for each can be found above each method.
 */
import Model.Staff;

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
         Method uses the parameters stored in the Staff object and creates an INSERT sql query
         to be used on the staff table. Note that we only check if some values are empty
         since some are required to be entered by user on the Front-End.

         Return Val: An INSERT query for aswindle.Staff.
      */
    public String createInsertMessage(){
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
            insertMessage = insertMessage.concat(staff.getDeptID()+",");

        }
        if(staff.isEmptyOffice()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(staff.getOffice()+",");

        }
        if(!staff.getTitle().equals("empty")){
            insertMessage = insertMessage.concat("'" + staff.getTitle() + "'");
        }
        else{
            insertMessage = insertMessage.concat("NULL");
        }


        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }
    /*
        Method takes fields from Staff object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.Staff.
     */
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
        if(!staff.getTitle().equals("empty")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Title = " + staff.getTitle());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }
        updateMessage = updateMessage.concat(" WHERE EID = " + staff.getID() + "\n");
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
