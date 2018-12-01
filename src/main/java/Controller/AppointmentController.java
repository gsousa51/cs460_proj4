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
            System.err.println("Invalid query");
            return "resultError";
        }
        return "resultAppointment";
    }

    @PostMapping("/updateAppointment")
    public String appointmentUpdate(Model model, @ModelAttribute Appointment appointment){
        AppointmentValidator appointmentValidator = new AppointmentValidator(appointment);
        model.addAttribute("validation", appointmentValidator);

        if(appointmentValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(appointmentValidator.getUpdateMessage());
            }catch(DataAccessException d){
                //TODO: Send user to an error page.
                System.err.println("****CAUGHT ERROR****");
                d.printStackTrace();
            }
            System.err.println("executed update query");
        }
        else{
            System.err.println("Invalid update message");
        }
        return "resultAppointment";
    }
}
