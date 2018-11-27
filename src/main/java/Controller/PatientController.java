package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Patient;

@Controller
public class PatientController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addPatient")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @GetMapping("/deletePatient")
    public String deletePatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "deletePatient";
    }
    @GetMapping("/updatePatient")
    public String updatePatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "updatePatient";
    }
    @PostMapping("/addPatient")
    public String patientAdd(@ModelAttribute Patient patient) {
        //TODO: Add business logic here
        return "resultPatient";
    }

    @PostMapping("/deletePatient")
    public String patientDelete(@ModelAttribute Patient patient){
        //TODO: Add business logic here
        System.out.println("Hit the delete endpoint");
        return "resultPatient";
    }

    @PostMapping("/updatePatient")
    public String patientUpdate(@ModelAttribute Patient patient){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is
        return "resultPatient";
    }
}
