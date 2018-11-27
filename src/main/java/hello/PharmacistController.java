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
        return "addpharmacist";
    }

    @GetMapping("/deletePharmacist")
    public String deletepharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "deletepharmacist";
    }

    @GetMapping("/updatePharmacist")
    public String updatepharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "updatepharmacist";
    }

    @PostMapping("/addPharmacist")
    public String pharmacistAdd(@ModelAttribute Pharmacist pharmacist) {
        //TODO: Add business logic here change to pharmacistResult.
        return "pharmacistResult";
    }



    @PostMapping("/deletePharmacist")
    public String pharmacistDelete(@ModelAttribute Pharmacist pharmacist){
        //TODO: Add business logic here
        return "pharmacistResult";
    }

    @PostMapping("/updatePharmacist")
    public String pharmacistUpdate(@ModelAttribute Pharmacist pharmacist){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is check for "" in the strings.
        return "pharmacistResult";
    }

}
