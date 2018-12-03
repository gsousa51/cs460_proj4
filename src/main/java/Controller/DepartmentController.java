package Controller;
/*
    Class: DepartmentController
    Students: Gary Sousa and Alex Swindle
    Group: Group1
    Assignment: Program 4
    Due: December 4th, 2018
    Class: Cs460 - Dr. Lester McCann - TAs Terrance Lim and Bailey Nottingham
    Purpose: This is the Controller that we're using for UPDATE pages that we're
    using manipulate the Department table. This class uses DepartmentValidator which can be found
    in the Validator package. We use it to validate the data the user is attempting to use to
    manipulate the Department table.
    This class also requires the project to have access to Java.SpringFramework so we can use its
    annotations/methods.
 */
import Validator.DepartmentValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Department;

@Controller
public class DepartmentController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @GetMapping("/updateDepartment")
    public String updateDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "updateDepartment";
    }

    /*
     Method is called when user hits submit at addDepartment, this endpoint will be hit.
     The parameters given are the model we're working with and an Department object
     which serves as the Bean for the user's input.

     We'll validate that this is a valid insertStatement and attempt to execute the query.

     If any errors occur during the insertion OR if this was determined to be an invalid INSERT
     by our validator, then we'll send the user to a page that tells them the query was invalid.

     Else we send the user to /resultDepartment.

     Return value: The URL to show.
  */
    @PostMapping("/updateDepartment")
    public String departmentUpdate(@ModelAttribute Department department){
        DepartmentValidator departmentValidator = new DepartmentValidator(department);

        if(departmentValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(departmentValidator.getUpdateMessage());
            }catch(DataAccessException d){
                d.printStackTrace();
                System.err.println("****CAUGHT ERROR****");
                return "resultError";
            }
            System.err.println("executed update query");
        }
        else{
            return "resultError";
        }
        return "resultDepartment";
    }
}
