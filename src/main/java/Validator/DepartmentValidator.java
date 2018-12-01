package Validator;

import Model.Department;

public class DepartmentValidator {

    private Department department;
    private String updateMessage;
    private boolean validUpdate;


    public DepartmentValidator(Department department){
        this.department = department;
        this.updateMessage = createUpdateMessage();
    }


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
