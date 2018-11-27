package Model;

public class Validator {

    private Appointment appointment;
    private CashData cashData;
    private Department department;
    private boolean valid;
    private String message;

    public Validator(Appointment appointment){
        this.appointment = appointment;
        validateAppointment();
    }

    public Validator(CashData cashData){
        this.cashData = cashData;
        validateCashData();
    }

    public Validator(Department department){
        this.department = department;
    }


    private void validateAppointment(){
        if(appointment.getExpDischarge()!= null){
            if(appointment.getAdmission().compareTo(appointment.getExpDischarge()) > 0){
               this.message = "Expected discharge cannot be before the admission date.";
               return;
            }
            if(appointment.getAdmission().compareTo(appointment.getActDischarge()) > 0){
                this.message = "Actual discharge cannot be before the admission date.";
                return;
            }
        }
        /*
            TODO: Check that the AID doesn't exist already.
            TODO: Check that DID/PID both exist
            TODO: Need to run a check on if room is going to be able to fit this patient.
         */
        this.valid = true;

    }
    private void validateCashData(){
        /*
            TODO: Validate the xActID is unique
            TODO: Validate EID exists.
            TODO: Validate PID exists.
            Everything else doesn't need validated.
         */
        this.valid = true;
    }

    private void validateDepartment(){
        /*
            TODO: Validate deptID exists
            TODO: Validate the office is free?
         */
        this.valid = true;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
