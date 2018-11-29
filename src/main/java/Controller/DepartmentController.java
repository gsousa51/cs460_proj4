package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Department;
import Model.DepartmentValidator;

@Controller
public class DepartmentController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addDepartment")
    public String addDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "addDepartment";
    }

    @GetMapping("/updateDepartment")
    public String updateDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "updateDepartment";
    }

    @PostMapping("/addDepartment")
    public String departmentAdd(@ModelAttribute Department department){
        DepartmentValidator validator = new DepartmentValidator(department);
        validator.validateAddDepartment();
        if(validator.isValid()) {
            jdbcTemplate.update("insert into aswindle.department values (?, ?, ?)",
                    department.getID(), department.getName(), department.getOffice());
        }
        return "resultDepartment";
    }

    @PostMapping("/updateDepartment")
    public String departmentUpdate(@ModelAttribute Department department){
        DepartmentValidator validator = new DepartmentValidator(department);
        validator.validateUpdateDepartment();
        if(validator.isValid()) {
            jdbcTemplate.update("update aswindle.department " +
                            "set dept_name = ?, office = ? " +
                            "where dept_id = ?",
                    department.getName(), department.getOffice(), department.getID());
        }
        return "resultDepartment";
    }
}
