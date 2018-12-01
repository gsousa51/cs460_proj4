package Controller;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Query2;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class Query2Controller {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/query2")
    public String query2Form(Model model) {
        model.addAttribute("query2", new Query2());
        return "query2";
    }

    @PostMapping("/query2")
    public String query2Submit(Model model, @ModelAttribute Query2 query) {
        List<String> allNames = this.jdbcTemplate.query(
                "SELECT d.f_name, d.l_name, d.office, b.building_name\n" +
                        "FROM aswindle.doctor d, aswindle.department dep, aswindle.building b, aswindle.room r\n" +
                        "WHERE dep.dept_name = ?\n" +
                        "AND d.dept_id = dep.dept_id\n" +
                        "AND d.office = r.room_id\n" +
                        "AND r.building_name = b.building_name", new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String first_name = rs.getString("f_name");
                        String last_name = rs.getString("l_name");
                        int office = rs.getInt("office");
                        String building_name = rs.getString("building_name");
                        return (String.format("Name: %s %s, Building %s Room %d", first_name, last_name, building_name, office));
                    }
                }, query.getDept_name());


        model.addAttribute("names", allNames);
        return "resultQuery2";
    }
}
