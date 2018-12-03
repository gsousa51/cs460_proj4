package Validator;

import Model.Department;
/*
    Class: DepartmentValidator
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This class is used in CashDataController, and is used to validate/create
    UPDATE queries on our CashData Table.

    Public Methods:
        getUpdateMessage()
        It used by DepartmentController, found in Controller package.
        Descriptions for each can be found above each method.
 */
public class DepartmentValidator {

    private Department department;
    private String updateMessage;
    private boolean validUpdate;


    public DepartmentValidator(Department department){
        this.department = department;
        this.updateMessage = createUpdateMessage();
    }

    /*
        Method takes fields from Department object to create an UPDATE SQL query.
        If there are no fields to add to the UPDATE query, then we say the UPDATE query is invalid.

        Return : String containing an UPDATE SQL query for aswindle.Department.
     */
    public String createUpdateMessage(){
        String updateMessage = "UPDATE aswindle.Department \nSET ";
        if(!department.isEmptyOffice()){
            updateMessage = updateMessage.concat("office = " + department.getOffice());
            this.validUpdate = true;
        }

        if(!department.getName().equals("")){
            if(this.validUpdate){
                updateMessage = updateMessage.concat(",");
            }
            updateMessage = updateMessage.concat("dept_name = '" + department.getName() + "'");
            this.validUpdate = true;
        }

        updateMessage = updateMessage.concat(" WHERE Dept_ID = " + department.getID() + "\n");
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }



    public boolean isValidUpdate() {
        return validUpdate;
    }

    public void setValidUpdate(boolean validUpdate) {
        this.validUpdate = validUpdate;
    }


}
