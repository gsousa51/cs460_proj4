package Controller;
/*
    Class: StaffController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for INSERT/UPDATE pages that we're
    using manipulate the Staff table. This class uses StaffValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Staff table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
import Validator.StaffValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Staff;

@Controller
public class StaffController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addStaff")
    public String addstaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "addStaff";
    }

    @GetMapping("/deleteStaff")
    public String deletestaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "deleteStaff";
    }

    @GetMapping("/updateStaff")
    public String updatestaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "updateStaff";
    }

    /*
    Method is called when user hits submit at addStaff, this endpoint will be hit.
    The parameters given are the model we're working with and an Staff object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid INSERT
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultStaff.

    Return value: The URL to show.
 */
    @PostMapping("/addStaff")
    public String staffAdd(@ModelAttribute Staff staff) {
        StaffValidator staffValidator = new StaffValidator(staff);
        if(staffValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(staffValidator.getInsertMessage());
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
        return "resultStaff";
    }

    /*
        Method simply queries our DB to delete the row whose column's PK matches the
        ID given in the Staff bean object sent in.

        Return value: "resultError" if query results in an error
                      "resultStaff" if query was successful.
     */

    @PostMapping("/deleteStaff")
    public String staffDelete(@ModelAttribute Staff staff){
        Object[] ID = {staff.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Staff WHERE EID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultStaff";
    }

    /*
    Method is called when user hits submit at updateStaff, this endpoint will be hit.
    The parameters given are the model we're working with and an Staff object
    which serves as the Bean for the user's input.

    We'll validate that this is a valid insertStatement and attempt to execute the query.

    If any errors occur during the insertion OR if this was determined to be an invalid UPDATE
    by our validator, then we'll send the user to a page that tells them the query was invalid.

    Else we send the user to /resultStaff.

    Return value: The URL to show.
 */
    @PostMapping("/updateStaff")
    public String staffUpdate(@ModelAttribute Staff staff){
        StaffValidator staffValidator = new StaffValidator(staff);
        if(staffValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(staffValidator.getUpdateMessage());
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
        return "resultStaff";
    }

}
