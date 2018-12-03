package Controller;

/*
    Class: DoctorController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for INSERT/UPDATE pages that we're
    using manipulate the Doctor table. This class uses DoctorValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Doctor table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Doctor;
import Validator.DoctorValidator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @GetMapping("/addDoctor")
    public String addDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());

        return "addDoctor";
    }

    @GetMapping("/deleteDoctor")
    public String deleteDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "deleteDoctor";
    }
    @GetMapping("/updateDoctor")
    public String updateDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "updateDoctor";
    }

    /*
    Method is called when user hits submit at addDoctor, this endpoint will be hit.
    The parameters given are the model we're working with and an Doctor object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid INSERT
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultDoctor.

    Return value: The URL to show.
 */
    @PostMapping("/addDoctor")
    public String doctorAdd(Model model, @ModelAttribute Doctor doctor) {
        DoctorValidator doctorValidator = new DoctorValidator(doctor);
        model.addAttribute(doctorValidator);
        if(doctorValidator.isValidInsert()) {
            try {

                this.jdbcTemplate.update(doctorValidator.getInsertMessage());

            }catch(DataAccessException d){
                System.err.println("*****CAUGHT ERROR*****");
                return "resultError";
            }
        }
        else{
            System.err.println("Invalid query");
            return "resultError";
        }
        return "resultDoctor";
    }

    /*
        Method simply queries our DB to delete the row whose column's PK matches the
        ID given in the Doctor bean object sent in.

        Return value: "resultError" if query results in an error
                      "resultDoctor" if query was successful.
     */
    @PostMapping("/deleteDoctor")
    public String doctorDelete(@ModelAttribute Doctor doctor){
        Object[] ID = {doctor.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Doctor WHERE DID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultDoctor";
    }

    /*
    Method is called when user hits submit at updateDoctor, this endpoint will be hit.
    The parameters given are the model we're working with and an Doctor object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid UPDATE
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultDoctor.

    Return value: The URL to show.
 */
    @PostMapping("/updateDoctor")
    public String doctorUpdate(Model model, @ModelAttribute Doctor doctor){
        DoctorValidator doctorValidator = new DoctorValidator(doctor);
        model.addAttribute("validation", doctorValidator);

        if(doctorValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(doctorValidator.getUpdateMessage());
            }catch(DataAccessException d){
                //TODO: Send user to an error page.
                System.err.println("****CAUGHT ERROR****");
                return "resultError";
            }
            System.err.println("executed update query");
        }
        else{
            System.err.println("Invalid update message");
            return "resultError";
        }
        return "resultDoctor";
    }
}
