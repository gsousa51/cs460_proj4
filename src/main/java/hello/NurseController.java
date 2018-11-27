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
/*
    @GetMapping("/deleteDoctor")
    public String deleteDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "deleteDoctor";
    }
    */
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


    /*
    @PostMapping("/deleteDoctor")
    public String doctorDelete(@ModelAttribute Doctor doctor){
        //TODO: Add business logic here
        System.out.println("Hit the delete endpoint");
        return "doctorResult";
    }
*/
    @PostMapping("/updateNurse")
    public String nurseUpdate(@ModelAttribute Nurse nurse){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is
        return "nurseResult";
    }

}
