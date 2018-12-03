package Controller;
/*
    Class: PatientController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for INSERT/UPDATE pages that we're
    using manipulate the Patient table. This class uses PatientValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Patient table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
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

    /*
    Method is called when user hits submit at addPatient, this endpoint will be hit.
    The parameters given are the model we're working with and an Patient object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid INSERT
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultPatient.

    Return value: The URL to show.
 */
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

    /*
        Method simply queries our DB to delete the row whose column's PK matches the
        ID given in the Patient bean object sent in.

        Return value: "resultError" if query results in an error
                      "resultPatient" if query was successful.
     */
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

    /*
Method is called when user hits submit at updatePatient, this endpoint will be hit.
The parameters given are the model we're working with and an Patient object
which serves as the Bean for the user's input.

We'll validate that this is a valid insertStatement and attempt to execute the query.

If any errors occur during the insertion OR if this was determined to be an invalid UPDATE
by our validator, then we'll send the user to a page that tells them the query was invalid.

Else we send the user to /resultPatient.

Return value: The URL to show.
*/
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
                return "resultError";
            }
            System.err.println("executed update query");
        }
        else{
            System.err.println("Invalid update message");
            return "resultError";
        }
        return "resultPatient";
    }
}
