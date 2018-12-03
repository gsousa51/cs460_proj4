package Controller;

/*
    Class: NurseController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for INSERT/UPDATE pages that we're
    using manipulate the Nurse table. This class uses NurseValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Nurse table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
import Validator.NurseValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Nurse;

@Controller
public class NurseController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addNurse")
    public String addNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "addNurse";
    }

    @GetMapping("/deleteNurse")
    public String deleteNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "deleteNurse";
    }

    @GetMapping("/updateNurse")
    public String updateNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "updateNurse";
    }
    /*
    Method is called when user hits submit at addNurse, this endpoint will be hit.
    The parameters given are the model we're working with and an Nurse object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid INSERT
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultNurse.

    Return value: The URL to show.
 */

    @PostMapping("/addNurse")
    public String nurseAdd(Model model, @ModelAttribute Nurse nurse) {
        NurseValidator nurseValidator = new NurseValidator(nurse);
        model.addAttribute(nurseValidator);
        if(nurseValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(nurseValidator.getInsertMessage());
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
        return "resultNurse";
    }

    /*
        Method simply queries our DB to delete the row whose column's PK matches the
        ID given in the Nurse bean object sent in.

        Return value: "resultError" if query results in an error
                      "resultNurse" if query was successful.
     */

    @PostMapping("/deleteNurse")
    public String nurseDelete(@ModelAttribute Nurse nurse){
        Object[] ID = {nurse.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Nurse WHERE NID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultNurse";
    }

    /*
    Method is called when user hits submit at updateNurse, this endpoint will be hit.
    The parameters given are the model we're working with and an Nurse object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid UPDATE
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultNurse.

    Return value: The URL to show.
 */
    @PostMapping("/updateNurse")
    public String nurseUpdate(Model model, @ModelAttribute Nurse nurse){
        NurseValidator nurseValidator = new NurseValidator(nurse);
        model.addAttribute("validation", nurseValidator);

        if(nurseValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(nurseValidator.getUpdateMessage());
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
        return "resultNurse";
    }

}
