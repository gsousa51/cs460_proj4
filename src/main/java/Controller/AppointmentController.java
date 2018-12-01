package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Appointment;
import Model.AppointmentValidator;

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
        validator.validateAddAppointment();
        model.addAttribute("appointmentValidator", validator);
        System.err.println("Added validator");
        if(validator.isValid()) {
            jdbcTemplate.update("insert into aswindle.appointment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    appointment.getAID(), appointment.getPID(), appointment.getDID(), appointment.getReason(),
                    appointment.getApptDate().getTime(), appointment.getAdmission().getTime(),
                    appointment.getExpDischarge().getTime(), appointment.getActDischarge().getTime(),
                    appointment.getRoom(), appointment.getTreatment());
        }
        return "resultAppointment";
    }

    @PostMapping("/updateAppointment")
    public String appointmentUpdate(Model model, @ModelAttribute Appointment appointment){
        AppointmentValidator validator = new AppointmentValidator(appointment);
        validator.validateUpdateAppointment();
        model.addAttribute("appointmentValidator", validator);
        // Not PID, AID, reason, appt_date
        if(validator.isValid()) {
            jdbcTemplate.update("update aswindle.appointment " +
                            "set DID = ?, admission = ?, exp_discharge = ?, act_discharge = ?, room = ?, treatment = ?" +

                            "where AID = ?",
                    appointment.getDID(), appointment.getAdmission().getTime(),
                    appointment.getExpDischarge().getTime(), appointment.getActDischarge().getTime(),
                    appointment.getRoom(), appointment.getTreatment(), appointment.getAID());
        }
        return "resultAppointment";
    }
}
