package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        //TODO: Add business logic here change to resultStaff.
        return "resultStaff";
    }



    @PostMapping("/deleteStaff")
    public String staffDelete(@ModelAttribute Staff staff){
        //TODO: Add business logic here
        return "resultStaff";
    }

    @PostMapping("/updateStaff")
    public String staffUpdate(@ModelAttribute Staff staff){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is check for "" in the strings.
        return "resultStaff";
    }

}
