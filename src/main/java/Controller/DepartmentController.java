package Controller;

import Validator.DepartmentValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Department;

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
        DepartmentValidator departmentValidator = new DepartmentValidator(department);

        if(departmentValidator.isValidUpdate()){
            try {
                this.jdbcTemplate.update(departmentValidator.getUpdateMessage());
            }catch(DataAccessException d){
                d.printStackTrace();
                System.err.println("****CAUGHT ERROR****");
                return "resultError";
            }
            System.err.println("executed update query");
        }
        else{
            return "resultError";
        }
        return "resultDepartment";
    }
}
