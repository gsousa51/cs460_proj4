package Controller;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import Model.Query4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class Query4Controller {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/query4")
    public String query4Form(Model model) {
        model.addAttribute("query4", new Query4());
        return "query4";
    }

    @PostMapping("/query4")
    public String query4Submit(Model model, @ModelAttribute Query4 query) {
        List<String> allNames = this.jdbcTemplate.query(
                "SELECT DISTINCT p.f_name, p.l_name, ph.medicine\n" +
                        "FROM aswindle.patient p, aswindle.pharm_data ph, aswindle.recept_data rd\n" +
                        "WHERE rd.eid = ?\n" +
                        "AND p.pid = rd.pid\n" +
                        "AND ph.pid = p.pid\n" +
                        "ORDER BY p.l_name", new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String first_name = rs.getString("f_name");
                        String last_name = rs.getString("l_name");
                        String medicine = rs.getString("medicine");
                        return (String.format("Name: %s %s, Medicine: %s", first_name, last_name, medicine));
                    }
                }, query.getEID());


        model.addAttribute("names", allNames);
        return "resultQuery4";
    }
}
