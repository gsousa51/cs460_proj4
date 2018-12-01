package Controller;

import Validator.CashDataValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.CashData;

@Controller
public class CashDataController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addCashData")
    public String addCashDataForm(Model model) {
        model.addAttribute("cashData", new CashData());
        return "addCashData";
    }

    @GetMapping("/updateCashData")
    public String updateCashDataForm(Model model) {
        model.addAttribute("cashData", new CashData());
        return "updateCashData";
    }

    @PostMapping("/addCashData")
    public String cashDataAdd(Model model, @ModelAttribute CashData cashData) {
        CashDataValidator cashDataValidator = new CashDataValidator(cashData);
        model.addAttribute(cashDataValidator);
        if(cashDataValidator.isValidInsert()) {
            try {
                this.jdbcTemplate.update(cashDataValidator.getInsertMessage());
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
        return "resultCashData";
    }

    @PostMapping("/updateCashData")
    public String cashDataUpdate(Model model, @ModelAttribute CashData cashData) {
        CashDataValidator cashDataValidator = new CashDataValidator(cashData);
        model.addAttribute("validation", cashDataValidator);

        if(cashDataValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(cashDataValidator.getUpdateMessage());
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
        return "resultCashData";
    }
}
