package Controller;

import Validator.NurseValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Nurse;

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
    public String nurseAdd(Model model, @ModelAttribute Nurse nurse) {
        NurseValidator nurseValidator = new NurseValidator(nurse);
        model.addAttribute(nurseValidator);
        if(nurseValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(nurseValidator.getInsertMessage());
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
        return "resultNurse";
    }



    @PostMapping("/deleteNurse")
    public String nurseDelete(@ModelAttribute Nurse nurse){
        Object[] ID = {nurse.getID()};
        try {
            this.jdbcTemplate.update("DELETE from aswindle.Nurse WHERE NID = ?", ID);
        }
        catch(DataAccessException d){
            d.printStackTrace();
            return "resultError";
        }
        return "resultNurse";
    }

    @PostMapping("/updateNurse")
    public String nurseUpdate(Model model, @ModelAttribute Nurse nurse){
        NurseValidator nurseValidator = new NurseValidator(nurse);
        model.addAttribute("validation", nurseValidator);

        if(nurseValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(nurseValidator.getUpdateMessage());
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
        return "resultNurse";
    }

}
