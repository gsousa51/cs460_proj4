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
public class DepartmentController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @GetMapping("/updateDepartment")
    public String updateDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "updateDepartment";
    }

    @PostMapping("/updateDepartment")
    public String departmentUpdate(@ModelAttribute Department department){
        //TODO: We need to figure out how to handle the fields were left empty
        //Most likely answer is
        return "resultDepartment";
    }
}
