package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.CashData;
import Model.Validator;

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
        jdbcTemplate.update("insert into aswindle.cash_data values (?, ?, ?, ?, ?, ?, ?)",
                cashData.getXactID(), cashData.getEID(), cashData.getPID(), cashData.getAmount(),
                cashData.getDueDate(), cashData.getStatus(), cashData.getPaidDate());
        return "resultCashData";
    }

    @PostMapping("/updateCashData")
    public String cashDataUpdate(Model model, @ModelAttribute CashData cashData) {
        model.addAttribute("validator", new Validator(cashData));
        jdbcTemplate.update("update aswindle.cash_data " +
                        "set eid = ?, pid = ?, amount = ?, due = ?, status = ?, paid = ? " +
                        "where xact_id = ?",
                cashData.getEID(), cashData.getPID(), cashData.getAmount(),
                cashData.getDueDate(), cashData.getStatus(), cashData.getPaidDate(), cashData.getXactID());
        return "resultCashData";
    }
}
