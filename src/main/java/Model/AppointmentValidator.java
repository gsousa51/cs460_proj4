package Model;

public class AppointmentValidator {
    private Appointment appointment;
    private boolean valid;

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

    private String message;

    public AppointmentValidator(Appointment appointment){
        this.appointment = appointment;
        valid = false;
        message = "";
    }

    public void validateAddAppointment(){
        if(appointment.getAID() == 0 || appointment.getPID() == 0 || appointment.getApptDate() == null ||
                appointment.getReason() == null){
            message = "Error: a required field was left blank (or zero).\n";
            return;
        }
        // Check dates
        if(appointment.getAdmission() != null){
            if(appointment.getAdmission().getTime() < appointment.getApptDate().getTime()){
                message = "Error: admission can't be before appointment date.\n";
                return;
            }
            if(appointment.getExpDischarge()!= null){
                if(appointment.getExpDischarge().getTime() < appointment.getAdmission().getTime()){
                    message = "Error: expected discharge can't be before admission.\n";
                    return;
                }
            }
            if(appointment.getActDischarge() != null){
                if(appointment.getActDischarge().getTime() < appointment.getAdmission().getTime()){
                    message = "Error: actual discharge can't be before admission.\n";
                    return;
                }
            }
        }
        // Check treatment
        if(appointment.getTreatment() != null){
            if(!appointment.getTreatment().equals("medical") && !appointment.getTreatment().equals("surgical") &&
                    !appointment.getTreatment().equals("physical therapy")){
                message = "Error: treatment must be 'medical', 'surgical', or 'physical therapy'\n";
                return;
            }
        }

        valid = true;
    }

    public void validateUpdateAppointment(){
        // Updates don't touch PID, Appt_Date, or Reason
        if(appointment.getAID() == 0){
            message = "Error: a required field was left blank (or zero).\n";
            return;
        }
        // Check dates
        if(appointment.getAdmission() != null){
            if(appointment.getAdmission().getTime() < appointment.getApptDate().getTime()){
                message = "Error: admission can't be before appointment date.\n";
                return;
            }
            if(appointment.getExpDischarge()!= null){
                if(appointment.getExpDischarge().getTime() < appointment.getAdmission().getTime()){
                    message = "Error: expected discharge can't be before admission.\n";
                    return;
                }
            }
            if(appointment.getActDischarge() != null){
                if(appointment.getActDischarge().getTime() < appointment.getAdmission().getTime()){
                    message = "Error: actual discharge can't be before admission.\n";
                    return;
                }
            }
        }
        // Check treatment
        if(appointment.getTreatment() != null){
            if(!appointment.getTreatment().equals("medical") && !appointment.getTreatment().equals("surgical") &&
                    !appointment.getTreatment().equals("physical theraphy")){
                message = "Error: treatment must be 'medical', 'surgical', or 'physical therapy'\n";
                return;
            }
        }

        valid = true;
    }

}
