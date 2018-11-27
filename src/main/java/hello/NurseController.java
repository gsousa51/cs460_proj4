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

    @PostMapping("/addNurse")
    public String doctorAdd(@ModelAttribute Nurse nurse) {
        //TODO: Add business logic here change to nurseResult.
        return "nurseResult";
    }



    @PostMapping("/deleteNurse")
    public String nurseDelete(@ModelAttribute Nurse nurse){
        //TODO: Add business logic here
        return "nurseResult";
    }

    @PostMapping("/updateNurse")
    public String nurseUpdate(@ModelAttribute Nurse nurse){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is check for "" in the strings.
        return "nurseResult";
    }

}