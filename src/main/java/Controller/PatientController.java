package Controller;

import Validator.PatientValidator;
import org.springframework.dao.DataAccessException;
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
    public String patientAdd(Model model, @ModelAttribute Patient patient) {
        PatientValidator patientValidator = new PatientValidator(patient);
        model.addAttribute(patientValidator);
        if(patientValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(patientValidator.getInsertMessage());
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
        return "resultPatient";
    }

    @PostMapping("/deletePatient")
    public String patientDelete(@ModelAttribute Patient patient){
        Object[] ID = {patient.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Patient WHERE PID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultPatient";
    }

    @PostMapping("/updatePatient")
    public String patientUpdate(Model model, @ModelAttribute Patient patient){
        PatientValidator patientValidator = new PatientValidator(patient);
        model.addAttribute("validation", patientValidator);

        if(patientValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(patientValidator.getUpdateMessage());
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
        return "resultPatient";
    }
}
