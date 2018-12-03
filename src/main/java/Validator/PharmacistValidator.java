package Validator;
/*
    Class: PharmacistValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used in PharmacistController, and is used to validate/create
    INSERT and UPDATE queries on our Pharmacist Table.

    Public Methods:
        getUpdateMessage(), getInsertMessage()
        It used by PharmacistController, found in Controller package.
        Descriptions for each can be found above each method.
 */
import Model.Pharmacist;

public class PharmacistValidator {

    private Pharmacist pharmacist;
    private String updateMessage;
    private String insertMessage;
    private boolean validUpdate;
    private boolean validInsert;

    public PharmacistValidator(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
        this.validInsert = true;
        this.updateMessage = createUpdateMessage();
    }

    /*
         Method uses the parameters stored in the Pharmacist object and creates an INSERT sql query
         to be used on the pharmacist table. Note that we only check if some values are empty
         since some are required to be entered by user on the Front-End.

         Return Val: An INSERT query for aswindle.Pharmacist.
      */
    public String createInsertMessage(){
        this.validInsert = true;
        String insertMessage = "INSERT INTO aswindle.pharmacist VALUES(";
        insertMessage = insertMessage.concat(pharmacist.getID()+",");
        insertMessage = insertMessage.concat("'" + pharmacist.getlName()+ "',");
        insertMessage = insertMessage.concat("'" + pharmacist.getfName()+ "',");
        insertMessage = insertMessage.concat(pharmacist.getDOB().getTime()+",");
        if(pharmacist.isEmptyOffice()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(pharmacist.getOffice()+",");

        }
        if(pharmacist.isEmptyDeptID()){
            insertMessage = insertMessage.concat("NULL,");
        }
        else{
            insertMessage = insertMessage.concat(pharmacist.getDeptID()+"");

        }

        insertMessage = insertMessage.concat(")");

        return insertMessage;
    }
    /*
        Method takes fields from Pharmacist object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.Pharmacist.
     */
    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Pharmacist \nSET ";

        if(!pharmacist.getlName().equals("")){
            validUpdate = true;
            updateMessage = updateMessage.concat("L_Name = '" + pharmacist.getlName()+"'");
            updateMessage = updateMessage.concat("\n");
        }
        if(!pharmacist.getfName().equals("")){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage =  updateMessage.concat("F_Name = '" + pharmacist.getfName()+"'");
            updateMessage =  updateMessage.concat("\n");
            validUpdate  = true;
        }

        if(!pharmacist.isEmptyDeptID()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Dept_ID = " + pharmacist.getDeptID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        if(!pharmacist.isEmptyOffice()){
            if(validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("Room = " + pharmacist.getOffice());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }

        updateMessage = updateMessage.concat(" WHERE PHID = " + pharmacist.getID() + "\n");
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
