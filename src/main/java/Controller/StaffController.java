package Controller;

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
