package Controller;

/*
    Class: PharmacistController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for INSERT/UPDATE pages that we're
    using manipulate the Pharmacist table. This class uses PharmacistValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Pharmacist table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
import Validator.PharmacistValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Pharmacist;

@Controller
public class PharmacistController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addPharmacist")
    public String addpharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "addPharmacist";
    }

    @GetMapping("/deletePharmacist")
    public String deletepharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "deletePharmacist";
    }

    @GetMapping("/updatePharmacist")
    public String updatepharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "updatePharmacist";
    }

    /*
    Method is called when user hits submit at addPharmacist, this endpoint will be hit.
    The parameters given are the model we're working with and an Pharmacist object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid INSERT
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultPharmacist.

    Return value: The URL to show.
 */
    @PostMapping("/addPharmacist")
    public String pharmacistAdd(Model model, @ModelAttribute Pharmacist pharmacist) {
        PharmacistValidator PharmacistValidator = new PharmacistValidator(pharmacist);
        model.addAttribute(PharmacistValidator);
        if(PharmacistValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(PharmacistValidator.getInsertMessage());
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
        return "resultPharmacist";
    }

    /*
        Method simply queries our DB to delete the row whose column's PK matches the
        ID given in the Pharmacist bean object sent in.

        Return value: "resultError" if query results in an error
                      "resultPharmacist" if query was successful.
     */

    @PostMapping("/deletePharmacist")
    public String pharmacistDelete(Model model, @ModelAttribute Pharmacist pharmacist){
        Object[] ID = {pharmacist.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Pharmacist WHERE PHID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultPharmacist";
    }

    /*
    Method is called when user hits submit at updatePharmacist, this endpoint will be hit.
    The parameters given are the model we're working with and an Pharmacist object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid UPDATE
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultPharmacist.

    Return value: The URL to show.
 */
    @PostMapping("/updatePharmacist")
    public String pharmacistUpdate(Model model, @ModelAttribute Pharmacist pharmacist){
        PharmacistValidator PharmacistValidator = new PharmacistValidator(pharmacist);
        model.addAttribute("validation", PharmacistValidator);

        if(PharmacistValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(PharmacistValidator.getUpdateMessage());
            }catch(DataAccessException d){
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
        return "resultPharmacist";
    }

}
