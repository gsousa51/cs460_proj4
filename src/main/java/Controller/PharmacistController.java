package Controller;

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

    @PostMapping("/addPharmacist")
    public String pharmacistAdd(@ModelAttribute Pharmacist pharmacist) {
        //TODO: Add business logic here change to resultPharmacist.
        return "resultPharmacist";
    }



    @PostMapping("/deletePharmacist")
    public String pharmacistDelete(@ModelAttribute Pharmacist pharmacist){
        //TODO: Add business logic here
        return "resultPharmacist";
    }

    @PostMapping("/updatePharmacist")
    public String pharmacistUpdate(@ModelAttribute Pharmacist pharmacist){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is check for "" in the strings.
        return "resultPharmacist";
    }

}
