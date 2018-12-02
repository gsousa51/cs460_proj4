package Controller;

import Validator.AppointmentValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Appointment;

@Controller
public class AppointmentController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addAppointment")
    public String addAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "addAppointment";
    }

    @GetMapping("/updateAppointment")
    public String updateAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "updateAppointment";
    }
    @PostMapping("/addAppointment")
    public String appointmentAdd(Model model, @ModelAttribute Appointment appointment) {
        AppointmentValidator validator = new AppointmentValidator(appointment);
        AppointmentValidator appointmentValidator = new AppointmentValidator(appointment);
        model.addAttribute(appointmentValidator);
        if(appointmentValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(appointmentValidator.getInsertMessage());
            }catch(DataAccessException d){
                System.err.println("*****CAUGHT ERROR*****");
                d.printStackTrace();
                return "resultError";
            }
        }
        else{
            System.err.println("Invalid Dates");
            return "resultError";
        }
        return "resultAppointment";
    }

    @PostMapping("/updateAppointment")
    public String appointmentUpdate(Model model, @ModelAttribute Appointment appointment){
        AppointmentValidator appointmentValidator = new AppointmentValidator(appointment);
        model.addAttribute("validation", appointmentValidator);

        if(appointmentValidator.isValidUpdate()){
            long apptDate = this.getDate("Appt_Date" , appointment.getAID());
            long admission = this.getDate("Admission" , appointment.getAID());
            long expDischarge = this.getDate("Exp_Discharge" , appointment.getAID());
            long actDischarge = this.getDate("Act_Discharge" , appointment.getAID());

            try {
                this.jdbcTemplate.update(appointmentValidator.getUpdateMessage());
            }catch(DataAccessException d){
                //TODO: Send user to an error page.
                System.err.println("****CAUGHT ERROR****");
                d.printStackTrace();
                return "resultError";
            }
            System.err.println("executed update query");
        }
        else{
            System.err.println("Invalid update message");
            return "resultError";
        }
        return "resultAppointment";
    }

    private long getDate(String colName, long AID){
        String query = "SELECT " + colName + " FROM aswindle.appointment WHERE AID = ?";
        String result = this.jdbcTemplate.queryForObject(
                query, new Object[] {AID}, String.class);
        System.out.println(colName + ": " + result);
        if(result == null){
            System.out.println("VALUE WAS NULL");
            return -1;
        }
        else{
            return Long.parseLong(result);
        }
    }
}
