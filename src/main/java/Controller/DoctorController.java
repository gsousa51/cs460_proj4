package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Doctor;

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
    @PostMapping("/addDoctor")
    public String doctorAdd(@ModelAttribute Doctor doctor) {
        //TODO: Add business logic here
        return "resultDoctor";
    }

    @PostMapping("/deleteDoctor")
    public String doctorDelete(@ModelAttribute Doctor doctor){
        //TODO: Add business logic here
        System.out.println("Hit the delete endpoint");
        return "resultDoctor";
    }

    @PostMapping("/updateDoctor")
    public String doctorUpdate(@ModelAttribute Doctor doctor){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is
        return "resultDoctor";
    }
}