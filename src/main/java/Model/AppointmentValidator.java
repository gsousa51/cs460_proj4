package Model;

public class AppointmentValidator {
    private Appointment appointment;
    private boolean valid;
    private String message;

    public AppointmentValidator(Appointment appointment){
        this.appointment = appointment;
        validateAppointment();
    }

    private void validateAppointment(){
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
                    !appointment.getTreatment().equals("physical theraphy")){
                message = "Error: treatment must be 'medical', 'surgical', or 'physical therapy'\n";
                return;
            }
        }

        valid = true;
    }

}
