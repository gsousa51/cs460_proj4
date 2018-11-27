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
        model.addAttribute("validator", new Validator(cashData));
        //TODO: Add business logic here
        return "resultCashData";
    }

    @PostMapping("/updateCashData")
    public String cashDataUpdate(Model model, @ModelAttribute CashData cashData){
        model.addAttribute("validator", new Validator(cashData));

        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is
        return "resultCashData";
    }
}
