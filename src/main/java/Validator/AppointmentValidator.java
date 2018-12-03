package Validator;

/*
    Class: AppointmentValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used as a validator for changes to the Appointment table.
    Each query that is ueed to update the table will have its parameters stored in a
    Bean that is an Appointment object. Using the fields of the Appointment object,
    we can decide if the query type is valid.

    Public Methods:
        getInsertMessage(), getUpdateMessage(), isValidUpdate(), and isValidInsert()
        ALL are used by AppointmentController, found in Controller package.
        Descriptions for each can be found above each method.
 */
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
        this.updateMessage = createUpdateMessage();
    }

    /*
        Method uses the parameters stored in the Appointment object and creates an INSERT sql query
        to be used on the appiointment table. Note that we only check if some values are empty
        since some are required to be entered by user on the Front-End.

        Return Val: An INSERT query for aswindle.appointment.
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

    /*
        Method takes fields from Appointment object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.appointment.
     */
    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Appointment \nSET ";


        if(!appointment.isEmptyDID()){
            updateMessage = updateMessage.concat("DID = " + appointment.getDID());
            updateMessage = updateMessage.concat("\n");
            validUpdate = true;
        }


        if(appointment.getAdmission() != null){
            //validUpdate will be true if param before this was added to query.
            //Thus, we'll need a comma. This logic is used below.
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


    /*
        Method is used to check if the dates we're using in the INSERT statement
        are valid.
        To be valid, they must meet the following critera:
            apptDate <= Admission <= (expDischarge && actDischarge)
        This logic is fairly straight forward, someone can't be admitted before their appointment
        nor can they be discharged before their appointment or their admission date.

        Return: True if it meets these critera, otherwise false.
     */
    public boolean validateInsertDates(){
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


    /*
        Method is used to check if we have valid parameters for an update message.
        The parameters are the current values for this appointment in the database.
        That is - if appointment.AID == 555, then the values sent in are the values found for
        each field for the row whose AID == 555.

        To be a valid appointment update, it must meet the following criteria:
            a) if admmission is to be updated, then apptDate <= admission
                1) if we are also updating either discharge, then apptDate <= admission <= discharges
                2) Otherwise, admission <= discharge dates currently in the database
            b) If admission is not being updated, then if we are updating either discharge dates,
               they must be less than the apptdate and admission sent in as parameters.

        Return: True if parameters fit the critera, false otherwise.

        NOTE: Because of how we pull in the parameters in AppointmentController, if any of the parameters
        have the value -1, it means their value is NULL in the database.
     */
    public boolean validateUpdate(long apptDate, long admission, long actDis, long expDis){
        //If apptDate is missing a value, then the AID did not exist in the db.
        //Therefore we say its an invalid UPDATE query.
        if(apptDate == -1){
            return false;
        }

        //If we're attempting to update admission
        if(appointment.getAdmission() != null){
            //It can't be before the appointment date.
            if(appointment.getAdmission().getTime() < apptDate){
                return false;
            }
            //If we're also attempting to update the discharge it can't be before the admission date.
            if(appointment.getExpDischarge() != null){
                if(appointment.getAdmission().getTime() > appointment.getExpDischarge().getTime()){
                    return false;
                }
            }
            else if(expDis != -1){
                if(appointment.getAdmission().getTime() > expDis){
                    return false;
                }
            }
            if(appointment.getActDischarge() != null){
                if(appointment.getAdmission().getTime() > appointment.getActDischarge().getTime()){
                    return false;
                }
            }
            else if(actDis != -1){
                if(appointment.getAdmission().getTime() > actDis){
                    return false;
                }
            }
        }
        else{
            //Else we're not trying to update admission, so if we're updating the discharges
            //we need to test their values again the values already in the DB.
            if(appointment.getExpDischarge() != null){
                if(admission != -1){
                    if(appointment.getExpDischarge().getTime() < admission){
                        return false;
                    }
                }
                else if(appointment.getExpDischarge().getTime() < apptDate){
                    return false;
                }
            }
            if(appointment.getActDischarge() != null){
                if(admission != -1){
                    if(appointment.getActDischarge().getTime() < admission){
                        return false;
                    }
                }
                else if(appointment.getActDischarge().getTime() < apptDate){
                    return false;
                }
            }
        }
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
