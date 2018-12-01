package Controller;

import Validator.PharmacistValidator;
import org.springframework.dao.DataAccessException;
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
    public String pharmacistAdd(Model model, @ModelAttribute Pharmacist pharmacist) {
        PharmacistValidator PharmacistValidator = new PharmacistValidator(pharmacist);
        model.addAttribute(PharmacistValidator);
        if(PharmacistValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(PharmacistValidator.getInsertMessage());
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
        return "resultPharmacist";
    }



    @PostMapping("/deletePharmacist")
    public String pharmacistDelete(Model model, @ModelAttribute Pharmacist pharmacist){
        Object[] ID = {pharmacist.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Pharmacist WHERE PHID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultPharmacist";
    }

    @PostMapping("/updatePharmacist")
    public String pharmacistUpdate(Model model, @ModelAttribute Pharmacist pharmacist){
        PharmacistValidator PharmacistValidator = new PharmacistValidator(pharmacist);
        model.addAttribute("validation", PharmacistValidator);

        if(PharmacistValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(PharmacistValidator.getUpdateMessage());
            }catch(DataAccessException d){
                //TODO: Send user to an error page.
                System.err.println("****CAUGHT ERROR****");
                d.printStackTrace();
            }
            System.err.println("executed update query");
        }
        else{
            System.err.println("Invalid update message");
        }
        return "resultPharmacist";
    }

}
